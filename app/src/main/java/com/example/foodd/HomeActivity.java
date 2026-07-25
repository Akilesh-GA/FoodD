package com.example.foodd;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    String searchHint = "";
    String greetingText = "";
    String currTime = "";

    TextView greetingTextView;
    RecyclerView foodRecyclerView;
    RecyclerView recyclerView;
    SearchView searchView;
    ImageView drawerIcon;
    ImageView closeIcon;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        foodRecyclerView = findViewById(R.id.food_recycler_view);
        recyclerView = findViewById(R.id.restaurant_recycler_view);
        searchView = findViewById(R.id.search_view);
        greetingTextView = findViewById(R.id.greeting_text);
        drawerIcon = findViewById(R.id.drawer_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        closeIcon = findViewById(R.id.close_icon);

        drawerIcon.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        closeIcon.setOnClickListener(view -> {
            drawerLayout.closeDrawer(GravityCompat.START);
        });


        greetingText = greetingTextView.getText().toString().trim();

        currTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        String[] time = currTime.split(":");
        int hours = Integer.parseInt(time[0]);

        if(hours >= 5 && hours < 12) {
            greetingText = "Hey Akil, Good Morning!";
        } else if(hours >= 12 && hours < 17) {
            greetingText = "Hey Akil, Good Afternoon!";
        } else if(hours >= 17 && hours < 21) {
            greetingText = "Hey Akil, Good Evening!";
        } else {
            greetingText = "Hey Akil,Good Night!";
        }

        SpannableString spannable = new SpannableString(greetingText);

        int start = greetingText.indexOf("G");
        int end = greetingText.length();

        spannable.setSpan(new StyleSpan(Typeface.BOLD),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ForegroundColorSpan(getColor(R.color.orange)),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        greetingTextView.setText(spannable);

        searchHint = getString(R.string.search_bar_hint).trim();

        @SuppressLint("RestrictedApi") SearchView.SearchAutoComplete searchText =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(searchHint);

        if (searchText != null) {
            searchText.setTextColor(ContextCompat.getColor(this, R.color.black));
            searchText.setHintTextColor(ContextCompat.getColor(this, R.color.mid_grey));
        }

        List<FoodEntity> foods = new ArrayList<>();

        foods.add(new FoodEntity(R.drawable.trending, "Trending"));

        foods.add(new FoodEntity(R.drawable.food_8, "Hot Dog"));

        foods.add(new FoodEntity(R.drawable.food_1, "Burger"));

        foods.add(new FoodEntity(R.drawable.food_2, "Donut"));

        foods.add(new FoodEntity(R.drawable.food_3, "Taco Salad Bowl"));

        foods.add(new FoodEntity(R.drawable.food_4, "Taco"));

        foods.add(new FoodEntity(R.drawable.food_5, "Dhokla"));

        foods.add(new FoodEntity(R.drawable.food_6, "Biryani"));

        foods.add(new FoodEntity(R.drawable.food_7, "Pav Bhaji"));

        foods.add(new FoodEntity(R.drawable.food_9, "Sandwich"));

        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        FoodAdapter foodAdapter = new FoodAdapter(foods);
        foodRecyclerView.setAdapter(foodAdapter);

        List<RestaurantEntity> restaurants = new ArrayList<>();
        List<RestaurantEntity> restaurantsFull = new ArrayList<>();

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_1, "Spicy World", "Biryani - Tandoori - BBQ",
                R.drawable.star, "3.9", R.drawable.truck, "₹50", R.drawable.clock, "32 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_2, "The Spice House", "Biryani - Kebab - Grill",
                R.drawable.star, "4.7", R.drawable.truck, "Free", R.drawable.clock, "25 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_3, "Pizza Corner", "Pizza - Pasta - Burgers",
                R.drawable.star, "4.4", R.drawable.truck, "₹40", R.drawable.clock, "30 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_4, "Burger Hub", "Burger - Fries - Shakes",
                R.drawable.star, "4.2", R.drawable.truck, "₹60", R.drawable.clock, "20 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_5, "South Indian Delight", "Dosa - Idli - Meals",
                R.drawable.star, "4.8", R.drawable.truck, "Free", R.drawable.clock, "18 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_6, "Ocean Grill", "Seafood - Grilled Fish - Prawns",
                R.drawable.star, "4.6", R.drawable.truck, "₹70", R.drawable.clock, "35 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_7, "Italian Kitchen", "Pizza - Pasta - Lasagna",
                R.drawable.star, "4.5", R.drawable.truck, "₹40", R.drawable.clock, "28 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_8, "Royal Biryani", "Hyderabadi Biryani - Kebabs",
                R.drawable.star, "4.9", R.drawable.truck, "Free", R.drawable.clock, "22 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_9, "Chinese Wok", "Noodles - Fried Rice - Momos",
                R.drawable.star, "4.3", R.drawable.truck, "₹30", R.drawable.clock, "27 min"));

        restaurants.add(new RestaurantEntity(R.drawable.restaurant_10, "Cafe Delight", "Coffee - Sandwiches - Desserts",
                R.drawable.star, "4.8", R.drawable.truck, "₹50", R.drawable.clock, "15 min"));

        // Restaurants Full

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_1, "Spicy World", "Biryani - Tandoori - BBQ",
                R.drawable.star, "3.9", R.drawable.truck, "₹50", R.drawable.clock, "32 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_2, "The Spice House", "Biryani - Kebab - Grill",
                R.drawable.star, "4.7", R.drawable.truck, "Free", R.drawable.clock, "25 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_3, "Pizza Corner", "Pizza - Pasta - Burgers",
                R.drawable.star, "4.4", R.drawable.truck, "₹40", R.drawable.clock, "30 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_4, "Burger Hub", "Burger - Fries - Shakes",
                R.drawable.star, "4.2", R.drawable.truck, "₹60", R.drawable.clock, "20 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_5, "South Indian Delight", "Dosa - Idli - Meals",
                R.drawable.star, "4.8", R.drawable.truck, "Free", R.drawable.clock, "18 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_6, "Ocean Grill", "Seafood - Grilled Fish - Prawns",
                R.drawable.star, "4.6", R.drawable.truck, "₹70", R.drawable.clock, "35 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_7, "Italian Kitchen", "Pizza - Pasta - Lasagna",
                R.drawable.star, "4.5", R.drawable.truck, "₹40", R.drawable.clock, "28 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_8, "Royal Biryani", "Hyderabadi Biryani - Kebabs",
                R.drawable.star, "4.9", R.drawable.truck, "Free", R.drawable.clock, "22 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_9, "Chinese Wok", "Noodles - Fried Rice - Momos",
                R.drawable.star, "4.3", R.drawable.truck, "₹30", R.drawable.clock, "27 min"));

        restaurantsFull.add(new RestaurantEntity(R.drawable.restaurant_10, "Cafe Delight", "Coffee - Sandwiches - Desserts",
                R.drawable.star, "4.8", R.drawable.truck, "₹50", R.drawable.clock, "15 min"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter adapter = new RestaurantAdapter(restaurants);
        recyclerView.setAdapter(adapter);
    }
}