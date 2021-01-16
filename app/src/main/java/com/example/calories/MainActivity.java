package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView add;
    TextView kcals;
    DBHelper dbHelper;
    int kcalsInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.img_add);
        kcalsInt = 0;
        dbHelper = new DBHelper(this);
        add.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add:
                Intent intent = new Intent(MainActivity.this, ShowMealsActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        kcals = findViewById(R.id.txt_kcal_amount);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(getString(R.string.table_name_chosen_meals),
                new String[]{getString(R.string.chosen_meals_kcal_amount)},
                getString(R.string.chosen_meals_is_deleted) + " = 0",
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
               kcalsInt += cursor.getInt(cursor.getColumnIndex(getString(R.string.chosen_meals_kcal_amount)));
            }while (cursor.moveToNext());
            kcals.setText(String.valueOf(kcalsInt));
        }
    }
}