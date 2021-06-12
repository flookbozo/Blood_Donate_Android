package com.example.myproject2.Rertofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://192.168.0.102:80/api/";
    public static final String API_BASE_URL = BASE_URL + "config.php/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if(retrofit == null) {
            Gson gson = new GsonBuilder()
                .setLenient()
                    .create();

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
