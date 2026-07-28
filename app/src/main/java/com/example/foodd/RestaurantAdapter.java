package com.example.foodd;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private final List<RestaurantEntity> restaurants;
    private final List<RestaurantEntity> restaurantsFull;

    public RestaurantAdapter(List<RestaurantEntity> restaurants) {
        this.restaurants = new ArrayList<>(restaurants);
        this.restaurantsFull = new ArrayList<>(restaurants);
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantEntity restaurant = restaurants.get(position);

        holder.restaurantImage.setImageResource(restaurant.getRestaurantImage());
        holder.restaurantName.setText(restaurant.getRestaurantName());
        holder.restaurantMenu.setText(restaurant.getRestaurantMenu());

        holder.rating.setText(restaurant.getRating());
        holder.deliveryCharge.setText(restaurant.getDeliveryCharge());
        holder.time.setText(restaurant.getTime());

        holder.ratingIcon.setImageResource(restaurant.getRatingIcon());
        holder.deliveryIcon.setImageResource(restaurant.getDeliveryIcon());
        holder.timeIcon.setImageResource(restaurant.getTimeIcon());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(String text) {

        restaurants.clear();

        if(text.isEmpty()) {
            restaurants.addAll(restaurantsFull);
        } else {
            text = text.trim().toLowerCase();
            for(RestaurantEntity restaurant : restaurantsFull) {
                if(restaurant.getRestaurantName().toLowerCase().contains(text)
                        || restaurant.getRestaurantMenu().toLowerCase().contains(text)) {
                    restaurants.add(restaurant);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantImage, ratingIcon, deliveryIcon, timeIcon;
        TextView restaurantName, restaurantMenu, rating, deliveryCharge, time;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantImage = itemView.findViewById(R.id.restaurant_img);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantMenu = itemView.findViewById(R.id.restaurant_menu);
            ratingIcon = itemView.findViewById(R.id.rating_icon);
            rating = itemView.findViewById(R.id.rating_text);
            deliveryIcon = itemView.findViewById(R.id.delivery_icon);
            deliveryCharge = itemView.findViewById(R.id.delivery_text);
            timeIcon = itemView.findViewById(R.id.time_icon);
            time = itemView.findViewById(R.id.time_text);
        }
    }
}
