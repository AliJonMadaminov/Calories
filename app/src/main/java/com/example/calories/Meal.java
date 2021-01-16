package com.example.calories;

public class Meal {
    int id;
    String imgUrl;
    String name;
    int caloriesPerPortion;
    float proteinAmount;
    float carbAmount;
    float fatAmount;

    public Meal(int id,String imgUrl, String name, int caloriesPerPortion, float proteinAmount, float carbAmount, float fatAmount) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.caloriesPerPortion = caloriesPerPortion;
        this.proteinAmount = proteinAmount;
        this.carbAmount = carbAmount;
        this.fatAmount = fatAmount;
    }
    public Meal(String imgUrl, String name, int caloriesPerPortion, float proteinAmount, float carbAmount, float fatAmount) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.caloriesPerPortion = caloriesPerPortion;
        this.proteinAmount = proteinAmount;
        this.carbAmount = carbAmount;
        this.fatAmount = fatAmount;
    }
}
