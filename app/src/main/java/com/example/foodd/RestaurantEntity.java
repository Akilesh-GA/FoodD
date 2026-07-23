package com.example.foodd;

public class RestaurantEntity {
    private int restaurantImage;
    private String restaurantName;
    private String restaurantMenu;
    private int ratingIcon;
    private String rating;
    private int deliveryIcon;
    private String deliveryCharge;
    private int timeIcon;
    private String time;

    RestaurantEntity(int restaurantImage, String restaurantName, String restaurantMenu, int ratingIcon,
                     String rating, int deliveryIcon, String deliveryCharge, int timeIcon, String time) {
        this.restaurantImage = restaurantImage;
        this.restaurantName = restaurantName;
        this.restaurantMenu = restaurantMenu;
        this.ratingIcon = ratingIcon;
        this.rating = rating;
        this.deliveryIcon = deliveryIcon;
        this.deliveryCharge = deliveryCharge;
        this.timeIcon = timeIcon;
        this.time = time;
    }

    public void setRestaurantImage(int restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantMenu(String restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }

    public void setRatingIcon(int ratingIcon) {
        this.ratingIcon = ratingIcon;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDeliveryIcon(int deliveryIcon) {
        this.deliveryIcon = deliveryIcon;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public void setTimeIcon(int timeIcon) {
        this.timeIcon = timeIcon;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRestaurantImage() {
        return this.restaurantImage;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public String getRestaurantMenu() {
        return this.restaurantMenu;
    }

    public int getRatingIcon() {
        return this.ratingIcon;
    }

    public String getRating() {
        return this.rating;
    }

    public int getDeliveryIcon() {
        return this.deliveryIcon;
    }

    public String getDeliveryCharge() {
        return this.deliveryCharge;
    }

    public int getTimeIcon() {
        return this.timeIcon;
    }

    public String getTime() {
        return this.time;
    }

}
