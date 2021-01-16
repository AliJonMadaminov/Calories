package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowMealDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView caloriesPerPortion;
    TextView proteinsPerPortion;
    TextView carbsPerPortion;
    TextView caloriesAmountText;
    TextView fatsPerPortion;
    ImageView mealImage;
    ImageView minus;
    ImageView plus;
    TextView portions;
    TextView addMealTxt;
    DBHelper dbHelper;
    int caloriesPerPortionInt;
    float proteinsPerPortionFloat;
    float carbsPerPortionFloat;
    float fatsPerPortionFloat;
    String chosenFoodName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meal_details);
        caloriesAmountText = findViewById(R.id.txt_calories_amount);
        caloriesPerPortion = findViewById(R.id.txt_kkals_per_portion);
        proteinsPerPortion = findViewById(R.id.txt_proteins);
        carbsPerPortion = findViewById(R.id.txt_carbs);
        fatsPerPortion = findViewById(R.id.txt_fats);
        mealImage = findViewById(R.id.img_meal_details);
        portions = findViewById(R.id.txt_portion_amount);
        plus = findViewById(R.id.img_plus);
        minus = findViewById(R.id.img_minus);
        addMealTxt = findViewById(R.id.btn_add);
        dbHelper = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("meals", null, "id = " + id, null, null, null, null);

        if (cursor.moveToFirst()) {
            chosenFoodName = cursor.getString(cursor.getColumnIndex((getString(R.string.name_meals_db))));
            caloriesPerPortionInt = cursor.getInt(
                    cursor.getColumnIndex(getString(R.string.kkal_per_portion_meals_db)));
            proteinsPerPortionFloat = cursor.getFloat(
                    cursor.getColumnIndex(getString(R.string.proteins_meals_db)));
            carbsPerPortionFloat = cursor.getFloat(
                    cursor.getColumnIndex(getString(R.string.carbs_meals_db)));
            fatsPerPortionFloat = cursor.getFloat(
                    cursor.getColumnIndex(getString(R.string.fats_meals_db)));


            caloriesPerPortion.setText(caloriesPerPortionInt + "");
            proteinsPerPortion.setText(proteinsPerPortionFloat + " g");
            carbsPerPortion.setText(carbsPerPortionFloat + " g");
            fatsPerPortion.setText(fatsPerPortionFloat + " g");
            Picasso.get().load(cursor.getString(
                    cursor.getColumnIndex(getString(R.string.image_url_db)))).into(mealImage);
        }
        db.close();
        dbHelper.close();
        cursor.close();
        caloriesAmountText.setText(String.valueOf(caloriesPerPortionInt));
        minus.setOnClickListener(this::onClick);
        plus.setOnClickListener(this::onClick);
        addMealTxt.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        int portionsInt = Integer.parseInt(portions.getText().toString());
        int caloriesUserChosenInt = Integer.parseInt(caloriesAmountText.getText().toString());
        switch (v.getId()) {
            case R.id.img_minus:
                if (portionsInt > 0 && caloriesUserChosenInt - caloriesPerPortionInt >= 0) {
                    portions.setText((portionsInt - 1) + "");
                    caloriesAmountText.setText((caloriesUserChosenInt - caloriesPerPortionInt) + "");
                }
                break;
            case R.id.img_plus:
                if (portionsInt < 15 && caloriesUserChosenInt <= caloriesPerPortionInt * 15) {
                    portions.setText((portionsInt + 1) + "");
                    caloriesAmountText.setText((caloriesUserChosenInt + caloriesPerPortionInt) + "");
                }
                break;
                case R.id.btn_add:
                    Intent intent = new Intent(ShowMealDetailsActivity.this, MainActivity.class);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(getString(R.string.chosen_meals_name), chosenFoodName);
                    cv.put(getString(R.string.chosen_meals_kcal_amount), caloriesUserChosenInt);
                    cv.put(getString(R.string.chosen_meals_proteins_amount), proteinsPerPortionFloat);
                    cv.put(getString(R.string.chosen_meals_carbs_amount), carbsPerPortionFloat);
                    cv.put(getString(R.string.chosen_meals_fats_amount), fatsPerPortionFloat);
                    db.insert(getString(R.string.table_name_chosen_meals), null, cv);
                    startActivity(intent);
                    finish();
                    break;
            default:
                break;
        }
    }
}