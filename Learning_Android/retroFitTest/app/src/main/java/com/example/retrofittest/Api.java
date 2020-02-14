package com.example.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Define interface, Retrofit will autogenerate methods to retrieve posts from API
//Define relative URL in interface
public interface Api {

    @GET("/finch_station.json")
    Call<JSONResponse> getFinch();

    @GET("/union_station.json")
    Call<JSONResponse> getUnion();

    @GET("/spadina_station.json")
    Call<JSONResponse> getSpadina();


}
