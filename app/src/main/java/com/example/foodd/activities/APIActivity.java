package com.example.foodd.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodd.adapters.TestAPIAdapter;
import com.example.foodd.R;
import com.example.foodd.api.RetrofitClient;
import com.example.foodd.api_test.TestAPI;
import com.example.foodd.api_test.TestAPIModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIActivity extends AppCompatActivity {
    Button FetchData;
    private RecyclerView recyclerView;
    private TestAPIAdapter adapter;

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
        adapter = new TestAPIAdapter(new TestAPIModel());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void fetchDataButtonSetup() {
        FetchData.setOnClickListener(view -> fetchData());
    }

    private void fetchData() {
        TestAPI testAPI = RetrofitClient.getInstance().create(TestAPI.class);

        testAPI.getTestData().enqueue(new Callback<TestAPIModel>() {
            @Override
            public void onResponse(Call<TestAPIModel> call, Response<TestAPIModel> response) {
                if(response.isSuccessful() && response.body() != null) {
                    TestAPIModel res = response.body();
                    adapter.setAPIData(res);
                }
            }

            @Override
            public void onFailure(Call<TestAPIModel> call, Throwable t) {
                Toast.makeText(APIActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
