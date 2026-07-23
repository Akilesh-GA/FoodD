package com.example.foodd;

public class FoodEntity {
    private int foodImage;
    private String foodName;

    public FoodEntity(int foodImage, String foodName) {
        this.foodImage = foodImage;
        this.foodName = foodName;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodImage() {
        return this.foodImage;
    }

    public String getFoodName() {
        return this.foodName;
    }
}
