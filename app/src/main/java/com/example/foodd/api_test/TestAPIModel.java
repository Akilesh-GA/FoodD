package com.example.foodd.api_test;

public class TestAPIModel {
    private int userId;
    private int id;
    private String title;
    private boolean isCompleted;

    public TestAPIModel() {

    }

    public TestAPIModel(int userId, int id, String title, boolean isCompleted) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }
}
