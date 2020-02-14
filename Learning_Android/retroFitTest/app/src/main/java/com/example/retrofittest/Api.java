package com.example.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/finch_station.json")
    Call<JSONResponse> getFinch();

    @GET("/union_station.json")
    Call<JSONResponse> getUnion();

    @GET("/spadina_station.json")
    Call<JSONResponse> getSpadina();


}
