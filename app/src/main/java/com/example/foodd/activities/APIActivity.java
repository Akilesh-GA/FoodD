package com.example.foodd.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodd.adapters.FoodAPIAdapter;
import com.example.foodd.R;
import com.example.foodd.api.FoodAPI;
import com.example.foodd.api.FoodAPIEntity;
import com.example.foodd.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIActivity extends AppCompatActivity {
    Button FetchData;
    private RecyclerView recyclerView;
    private FoodAPIAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.api_test_layout);

        initViews();
        adapterSetup();
        fetchDataButtonSetup();
    }

    private void initViews() {
        FetchData = findViewById(R.id.fetch_data);
        recyclerView = findViewById(R.id.api_recycler_view);
    }

    private void adapterSetup() {
        adapter = new FoodAPIAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void fetchDataButtonSetup() {
        FetchData.setOnClickListener(view -> fetchData());
    }

    private void fetchData() {
        FoodAPI foodAPI = RetrofitClient.getInstance().create(FoodAPI.class);

        foodAPI.getFoods().enqueue(new Callback<List<FoodAPIEntity>>() {
            @Override
            public void onResponse(Call<List<FoodAPIEntity>> call, Response<List<FoodAPIEntity>> response) {
                Log.d("API", "Code: " + response.code());
                if(response.isSuccessful() && response.body() != null) {
                    List<FoodAPIEntity> food = response.body();
                    adapter.setFoods(food);
                }
            }

            @Override
            public void onFailure(Call<List<FoodAPIEntity>> call, Throwable t) {
                Toast.makeText(APIActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
