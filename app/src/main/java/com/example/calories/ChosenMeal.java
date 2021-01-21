package com.example.calories;

public class ChosenMeal {
    String name;
    int totalCalories;
    String dateOfChosing;

    public ChosenMeal(String name, int caloriesAmount, String dateOfChosing) {
        this.name = name;
        this.totalCalories = caloriesAmount;
        this.dateOfChosing = dateOfChosing;
    }
}
