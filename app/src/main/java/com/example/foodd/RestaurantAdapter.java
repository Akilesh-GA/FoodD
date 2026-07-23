package com.example.foodd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<RestaurantEntity> restaurants;

    public RestaurantAdapter(List<RestaurantEntity> restaurants) {
        this.restaurants = restaurants;
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

        holder.ratingIcon.setImageResource(R.drawable.star);
        holder.deliveryIcon.setImageResource(R.drawable.truck);
        holder.timeIcon.setImageResource(R.drawable.clock);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
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
