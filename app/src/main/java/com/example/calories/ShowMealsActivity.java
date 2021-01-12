package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ShowMealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meals);

        recyclerView = findViewById(R.id.recycler_meal);

        MealAdapter adapter = new MealAdapter();

        for (int i = 0; i < 5; i++) {
            adapter.addMeal(new Meal("https://i.pinimg.com/originals/6e/21/0c/6e210c0ea82d1fff53f03140a9eba418.jpg", "Palov", 359, 10f, 76f, 1.4f));
            adapter.addMeal(new Meal("https://rutxt.ru/files/14589/original/8c7a6c097d.JPG", "Curd", 98, 11f, 3.4f, 4.3f));
            adapter.addMeal(new Meal("https://images.heb.com/is/image/HEBGrocery/000032968", "Kefir", 139, 8 ,9 , 8));

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        MealOnClickListener listener = new MealOnClickListener() {
            @Override
            public void onClick(Meal meal) {

            }
        };
    }
}