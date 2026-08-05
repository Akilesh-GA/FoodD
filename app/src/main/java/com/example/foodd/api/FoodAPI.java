package com.example.foodd.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodAPI {
    @GET("food/items")
    Call<List<FoodAPIEntity>> getFoods();
}
