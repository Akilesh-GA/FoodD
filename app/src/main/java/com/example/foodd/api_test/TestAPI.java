package com.example.foodd.api_test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestAPI {
    @GET("todos/1")
    Call<TestAPIModel> getTestData();
}
