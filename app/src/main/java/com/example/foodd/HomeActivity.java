package com.example.foodd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        RecyclerView foodRecyclerView = findViewById(R.id.food_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.restaurant_recycler_view);
        SearchView searchView = findViewById(R.id.search_view);

        @SuppressLint("RestrictedApi") SearchView.SearchAutoComplete searchText =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text);

        searchText.setTextColor(ContextCompat.getColor(this, R.color.orange));
        searchText.setHintTextColor(ContextCompat.getColor(this, R.color.mid_grey));

        List<FoodEntity> foods = new ArrayList<>();

        foods.add(new FoodEntity(R.drawable.trending,
                "Trending")
        );

        foods.add(new FoodEntity(R.drawable.food_8,
                "Hot Dog")
        );

        foods.add(new FoodEntity(R.drawable.food_1,
                "Burger")
        );

        foods.add(new FoodEntity(R.drawable.food_2,
                "Donut")
        );

        foods.add(new FoodEntity(R.drawable.food_3,
                "Taco Salad Bowl")
        );

        foods.add(new FoodEntity(R.drawable.food_4,
                "Taco")
        );

        foods.add(new FoodEntity(R.drawable.food_5,
                "Dhokla")
        );

        foods.add(new FoodEntity(R.drawable.food_6,
                "Biryani")
        );

        foods.add(new FoodEntity(R.drawable.food_7,
                "Pav Bhaji")
        );

        foods.add(new FoodEntity(R.drawable.food_9,
                "Sandwich")
        );

        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FoodAdapter foodAdapter = new FoodAdapter(foods);

        foodRecyclerView.setAdapter(foodAdapter);

        List<RestaurantEntity> restaurants = new ArrayList<>();
        List<RestaurantEntity> restaurantsFull = new ArrayList<>();

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_1,
                "Spicy World",
                "Biryani - Tandoori - BBQ",
                R.drawable.star, "3.9",
                R.drawable.truck, "₹50",

                R.drawable.clock, "32 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_2,
                "The Spice House",
                "Biryani - Kebab - Grill",
                R.drawable.star,
                "4.7",
                R.drawable.truck,
                "Free",
                R.drawable.clock,
                "25 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_3,
                "Pizza Corner",
                "Pizza - Pasta - Burgers",
                R.drawable.star,
                "4.4",
                R.drawable.truck,
                "₹40",
                R.drawable.clock,
                "30 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_4,
                "Burger Hub",
                "Burger - Fries - Shakes",
                R.drawable.star,
                "4.2",
                R.drawable.truck,
                "₹60",
                R.drawable.clock,
                "20 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_5,
                "South Indian Delight",
                "Dosa - Idli - Meals",
                R.drawable.star,
                "4.8",
                R.drawable.truck,
                "Free",
                R.drawable.clock,
                "18 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_6,
                "Ocean Grill",
                "Seafood - Grilled Fish - Prawns",
                R.drawable.star,
                "4.6",
                R.drawable.truck,
                "₹70",
                R.drawable.clock,
                "35 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_7,
                "Italian Kitchen",
                "Pizza - Pasta - Lasagna",
                R.drawable.star,
                "4.5",
                R.drawable.truck,
                "₹40",
                R.drawable.clock,
                "28 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_8,
                "Royal Biryani",
                "Hyderabadi Biryani - Kebabs",
                R.drawable.star,
                "4.9",
                R.drawable.truck,
                "Free",
                R.drawable.clock,
                "22 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_9,
                "Chinese Wok",
                "Noodles - Fried Rice - Momos",
                R.drawable.star,
                "4.3",
                R.drawable.truck,
                "₹30",
                R.drawable.clock,
                "27 min"
        ));

        restaurants.add(new RestaurantEntity(
                R.drawable.restaurant_10,
                "Cafe Delight",
                "Coffee - Sandwiches - Desserts",
                R.drawable.star,
                "4.8",
                R.drawable.truck,
                "₹50",
                R.drawable.clock,
                "15 min"
        ));

        // Restaurants Full

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_1,
                "Spicy World",
                "Biryani - Tandoori - BBQ",
                R.drawable.star, "3.9",
                R.drawable.truck, "₹50",
                R.drawable.clock, "32 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_2,
                "The Spice House",
                "Biryani - Kebab - Grill",
                R.drawable.star,
                "4.7",
                R.drawable.truck,
                "Free",
                R.drawable.clock,
                "25 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_3,
                "Pizza Corner",
                "Pizza - Pasta - Burgers",
                R.drawable.star,
                "4.4",
                R.drawable.truck,
                "₹40",
                R.drawable.clock,
                "30 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_4,
                "Burger Hub",
                "Burger - Fries - Shakes",
                R.drawable.star,
                "4.2",
                R.drawable.truck,
                "₹60",
                R.drawable.clock,
                "20 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_5,
                "South Indian Delight",
                "Dosa - Idli - Meals",
                R.drawable.star,
                "4.8",
                R.drawable.truck,
                "Free",
                R.drawable.clock,
                "18 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_6,
                "Ocean Grill",
                "Seafood - Grilled Fish - Prawns",
                R.drawable.star,
                "4.6",
                R.drawable.truck,
                "₹70",
                R.drawable.clock,
                "35 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_7,
                "Italian Kitchen",
                "Pizza - Pasta - Lasagna",
                R.drawable.star,
                "4.5",
                R.drawable.truck,
                "₹40",
                R.drawable.clock,
                "28 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_8,
                "Royal Biryani",
                "Hyderabadi Biryani - Kebabs",
                R.drawable.star,
                "4.9",
                R.drawable.truck,
                "Free",
                R.drawable.clock,
                "22 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_9,
                "Chinese Wok",
                "Noodles - Fried Rice - Momos",
                R.drawable.star,
                "4.3",
                R.drawable.truck,
                "₹30",
                R.drawable.clock,
                "27 min"
        ));

        restaurantsFull.add(new RestaurantEntity(
                R.drawable.restaurant_10,
                "Cafe Delight",
                "Coffee - Sandwiches - Desserts",
                R.drawable.star,
                "4.8",
                R.drawable.truck,
                "₹50",
                R.drawable.clock,
                "15 min"
        ));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter adapter = new RestaurantAdapter(restaurants);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.filterList(newText);

                return true;
            }
        });
    }
}