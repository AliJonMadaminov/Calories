package com.example.calories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class AddMealActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ImageView mealImage;
    Button addImage;
    EditText mealName;
    EditText kcalsPerPortion;
    EditText proteinsPerPortion;
    EditText carbsPerPortion;
    Uri imageUri;
    EditText fatsPerPortion;
    TextView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        dbHelper = new DBHelper(this);
        mealImage = findViewById(R.id.img_meal_add);
        addImage = findViewById(R.id.btn_add_image);
        mealName = findViewById(R.id.edt_meal_name);
        kcalsPerPortion = findViewById(R.id.edt_kcals_per100);
        proteinsPerPortion = findViewById(R.id.edt_proteins);
        carbsPerPortion = findViewById(R.id.edt_carbs);
        fatsPerPortion = findViewById(R.id.edt_fats);
        add = findViewById(R.id.txt_add_new_meal);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, RequestCode.PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RequestCode.PICK_IMAGE) {
            mealImage.setImageURI(data.getData());
            imageUri = data.getData();
        }
    }
}
//SQLiteDatabase db = dbHelper.getWritableDatabase();
//            ContentValues cv = new ContentValues();
//            cv.put("name", mealName.getText().toString());
//            db.insert("meals", null, )