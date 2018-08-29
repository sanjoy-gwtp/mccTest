package com.mcctest.serverclient;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcctest.adapter.DateTypeAdapter;
import com.mcctest.utills.Session;
import com.mcctest.widget.ProgressHUD;



import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RestClientBuilder {

    public static Retrofit createClient() {
        return createClient(null);
    }

    public static Retrofit createClient(Context context) {

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(1, TimeUnit.MINUTES);
        httpClientBuilder.readTimeout(1, TimeUnit.MINUTES);
        httpClientBuilder.writeTimeout(1, TimeUnit.MINUTES);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder();
                if(Session.getToken() != null) {
                    requestBuilder.addHeader("token",Session.getToken());
                    requestBuilder.addHeader("username",Session.getUsername());
                    requestBuilder.addHeader("password",Session.getPassword());
                }

                return chain.proceed(requestBuilder.build());
            }
        });

        if(context != null) {
            ProgressHUD mProgressHUD = ProgressHUD.show(context,"Loading", true);
            httpClientBuilder.addNetworkInterceptor(new ProgressInterceptor(mProgressHUD));
//            ProgressHUD  mProgressHUD = ProgressHUD.show(context,"Loading", true);
        }

        return new Retrofit.Builder()
                .baseUrl(UrlConstants.SERVER_IP)
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static <T> T createService(Class serviceClass) {
        return (T) createService(null,serviceClass);
    }

    public static <T> T createService(Context context, Class serviceClass) {
        return (T) createClient(context).create(serviceClass);
    }

    public static <T> Call<T> createOtpCall(Call<T> call , final String secrect, final String token) {

        final Call<T> clonedCall = call.clone();

        return new Call<T>(){

            @Override
            public Response<T> execute() throws IOException {
                return clonedCall.execute();
            }

            @Override
            public void enqueue(Callback<T> callback) {
                clonedCall.enqueue(callback);
            }

            @Override
            public boolean isExecuted() {
                return clonedCall.isExecuted();
            }

            @Override
            public void cancel() {
                clonedCall.cancel();
            }

            @Override
            public boolean isCanceled() {
                return clonedCall.isCanceled();
            }

            @Override
            public Call<T> clone() {
                return clone();
            }

            @Override
            public Request request() {
                Request.Builder requestBuilder = clonedCall.request().newBuilder();
                requestBuilder.addHeader("otp",token);
                requestBuilder.addHeader("requestId",secrect);
                return requestBuilder.build();
            }
        };
    }

    private static class ProgressInterceptor implements Interceptor{


        private ProgressHUD mProgressHUD;


        public ProgressInterceptor(ProgressHUD mProgressHUD) {
            this.mProgressHUD = mProgressHUD;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request().newBuilder().build());
            okhttp3.Response response = originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), null)).build();

            mProgressHUD.dismiss();

            return response;
        }
    }


    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final RestRequestListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, RestRequestListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    System.out.println("Response Length " + responseBody.contentLength());
//                    progressListener.loaded(totalBytesRead * 100 / responseBody.contentLength());
                    return bytesRead;
                }
            };
        }
    }
}
