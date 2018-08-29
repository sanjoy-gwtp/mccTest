package com.mcctest.serverclient.serviceInterface;

import com.mcctest.serverclient.UrlConstants;
import com.mcctest.serverclient.request.ImageRequest;
import com.mcctest.serverclient.response.ImageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by sanjoy on 8/29/18.
 */

public interface ImageService {
    @POST(UrlConstants.IMAGE_URL)
    @Headers("Content-Type: application/json")
    Call<ImageResponse> getImage(@Body ImageRequest request);
}
