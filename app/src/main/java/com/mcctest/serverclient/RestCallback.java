package com.mcctest.serverclient;

import android.app.Activity;


import com.mcctest.serverclient.response.ErrorResponse;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sanjoy on 5/21/17.
 */

public abstract class RestCallback<T> implements Callback<T> {

    private final GsonConverterFactory gsonConverter;
    private Activity activity;
    private Call<T> originalCall;

    public RestCallback() {
        this(null);
    }

    public RestCallback(Activity activity) {
        this.activity = activity;
        this.gsonConverter = GsonConverterFactory.create();
    }

    public abstract  void onSuccess(T result);

    public abstract  void onError(ErrorResponse errorResponse);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.originalCall = call;
        try {
            if (response.isSuccessful()) {
                onSuccess(response.body());
            } else {
                onError(createErrorResponse(response));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();

        if(t instanceof SocketTimeoutException){
            onError(new ErrorResponse("","Service is not available"));
        }
        else {
            onError(new ErrorResponse("","Unhandled exception"));
        }
    }

    public void reCall(){
        if(originalCall != null) {
            originalCall.clone().enqueue(this);
        }
    }

//    public void callWithOtpToken(OtpToken otpToken) {
//        Session.setOtpToken(otpToken);
//        //Call<T> call =  RestClientBuilder.createOtpCall(originalCall,otpToken.getRequestId(),otpToken.getOtp());
//        reCall();
//    }

    public ErrorResponse createErrorResponse(Response<T> response) {
//        Converter<ResponseBody, ErrorResponse> converter = RestClientBuilder
//                .createClient()
//                .responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        try {
           // ErrorResponse errorResponse = converter.convert(response.errorBody());
          //  errorResponse.setHttpStatus(response.code());
           // return errorResponse;
          String errorMessage =   response.headers().get("reason");
          int httpStatus = response.code();
          return (new ErrorResponse(httpStatus , "9001", errorMessage));
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(response.code(), "",e.getMessage());
        }
    }

}
