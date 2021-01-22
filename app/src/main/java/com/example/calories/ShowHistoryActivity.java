package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class ShowHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MealHistoryAdapter adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper = new DBHelper(getApplicationContext());
        recyclerView = findViewById(R.id.recycler_meal_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        initializeAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void initializeAdapter() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        adapter = new MealHistoryAdapter();
        Cursor cursor = db.query(getString(R.string.table_name_chosen_meals),
                null, getString(R.string.chosen_meals_is_deleted) + " = ?",
                new String[]{"0"}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(getString(R.string.chosen_meals_name)));
                String date = cursor.getString(cursor.getColumnIndex(getString(R.string.chosen_meals_date)));
                int caloriesAmount = cursor.getInt(cursor.getColumnIndex(getString(R.string.chosen_meals_kcal_amount)));
                String imageUri = cursor.getString(cursor.getColumnIndex(getString(R.string.chosen_meals_image_uri)));
                adapter.addChosenMeal(new ChosenMeal(name, caloriesAmount, date, imageUri));
            } while (cursor.moveToNext());
        }
        db.close();
    }
}