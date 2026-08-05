package com.example.foodd.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodd.R;
import com.example.foodd.api.FoodAPIEntity;

import java.util.List;


public class FoodAPIAdapter extends RecyclerView.Adapter<FoodAPIAdapter.FoodAPIHolder> {
    private List<FoodAPIEntity> foods;

    public FoodAPIAdapter(List<FoodAPIEntity> foods) {
        this.foods = foods;
    }

    public void setFoods(List<FoodAPIEntity> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodAPIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.api_food_item, parent, false);
        return new FoodAPIHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAPIHolder holder, int position) {
        FoodAPIEntity food = foods.get(position);

        String id = String.valueOf(food.getId());
        String foodName = food.getName();
        String price = String.valueOf(food.getPrice());

        holder.Id.setText(id);
        holder.Name.setText(foodName);
        holder.Price.setText(price);
    }

    @Override
    public int getItemCount() {
        return foods == null ? 0 : this.foods.size();
    }

    public static class FoodAPIHolder extends RecyclerView.ViewHolder {
        TextView Id, Name, Price;

        public FoodAPIHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.ID);
            Name = itemView.findViewById(R.id.name);
            Price = itemView.findViewById(R.id.price);
        }
    }
}
