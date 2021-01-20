package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;

public class ShowMealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    MealOnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meals);

        recyclerView = findViewById(R.id.recycler_meal);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("meals", null, null, null, null, null, null);
        implementMealOnClickListener();
        MealAdapter adapter = new MealAdapter(listener);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(getString(R.string.id_meals_db));
                int imageUrlIndex = cursor.getColumnIndex(getString(R.string.image_url_db));
                int nameIndex = cursor.getColumnIndex(getString(R.string.name_meals_db));
                int kkalPerPortionIndex = cursor.getColumnIndex(getString(R.string.kkal_per_portion_meals_db));
                int proteinIndex = cursor.getColumnIndex(getString(R.string.proteins_meals_db));
                int carbsIndex = cursor.getColumnIndex(getString(R.string.carbs_meals_db));
                int fatsIndex = cursor.getColumnIndex(getString(R.string.fats_meals_db));
                adapter.addMeal(new Meal(cursor.getInt(idIndex),cursor.getString(imageUrlIndex),
                        cursor.getString(nameIndex), cursor.getInt(kkalPerPortionIndex),
                        cursor.getFloat(proteinIndex), cursor.getFloat(carbsIndex),
                        cursor.getFloat(fatsIndex)));

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
    public void implementMealOnClickListener() {
         listener = new MealOnClickListener() {
            @Override
            public void onClick(Meal meal) {
                Intent intent = new Intent(ShowMealsActivity.this, ShowMealDetailsActivity.class);
                intent.putExtra("id", meal.id);
                Log.d("ShowMeals id = ", String.valueOf(meal.id));
                startActivity(intent);
                finish();
            }

            @Override
            public void onClickFavourite(Meal meal) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("id", meal.id);
                db.insert(getString(R.string.table_name_favourite_meals), null, cv);
            }
        };
    }
}