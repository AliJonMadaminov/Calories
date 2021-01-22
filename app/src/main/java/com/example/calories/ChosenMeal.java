package com.example.calories;

public class ChosenMeal {
    String name;
    int totalCalories;
    String dateOfChosing;
    String imgUri;

    public ChosenMeal(String name, int caloriesAmount, String dateOfChosing, String img_uri) {
        this.name = name;
        this.totalCalories = caloriesAmount;
        this.dateOfChosing = dateOfChosing;
        this.imgUri = img_uri;
    }
}
