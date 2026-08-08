package com.example.foodd.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodd.R;
import com.example.foodd.api_test.TestAPIModel;


public class TestAPIAdapter extends RecyclerView.Adapter<TestAPIAdapter.TestAPIHolder> {
    private TestAPIModel data;

    public TestAPIAdapter(TestAPIModel data) {
        this.data = data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAPIData(TestAPIModel data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestAPIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.api_food_item, parent, false);
        return new TestAPIHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAPIHolder holder, int position) {

        String id = String.valueOf(data.getId());
        String userId = String.valueOf(data.getUserId());
        String title = String.valueOf(data.getTitle());
        String status = String.valueOf(data);

        boolean isCompleted = status.equals("true");

        holder.Id.setText(id);
        holder.UserId.setText(userId);
        holder.Title.setText(title);
        holder.isCompleted.setText(String.valueOf(isCompleted));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : 1;
    }

    public static class TestAPIHolder extends RecyclerView.ViewHolder {
        TextView UserId, Id, Title, isCompleted;

        public TestAPIHolder(@NonNull View itemView) {
            super(itemView);

            Id = itemView.findViewById(R.id.id);
            UserId = itemView.findViewById(R.id.user_id);
            Title = itemView.findViewById(R.id.title);
            isCompleted = itemView.findViewById(R.id.is_completed);
        }
    }
}
