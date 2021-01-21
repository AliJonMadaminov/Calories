package com.example.calories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public DBHelper(@Nullable Context context) {
        super(context, "MealsDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table meals ("
        + "id integer primary key autoincrement,"
        + "name text not null unique,"
        + "kkal_per_portion integer not null,"
        + "proteins real,"
        + "carbs real,"
        + "fats real,"
        + "is_favourite integer not null default 0,"
        + "image_uri text unique default null,"
        + "image_url text not null unique );" );

        db.execSQL("create table chosen_meals ("
                + "id integer primary key autoincrement,"
                + "name text not null,"
                + "kcal_amount integer not null,"
                + "proteins_amount real,"
                + "carbs_amount real,"
                + "fats_amount real,"
                + "date Text not null,"
                + "is_deleted integer not null default 0 );" );

        db.execSQL("create table favourite_meals ("
                + "id integer,"
                + "foreign key (id) references meals(id) );" );

        this.db = db;

        addMeal(new Meal("https://i.pinimg.com/originals/6e/21/0c/6e210c0ea82d1fff53f03140a9eba418.jpg",
                "Palov", 359, 10f, 76f, 1.4f));

        addMeal(new Meal("https://rutxt.ru/files/14589/original/8c7a6c097d.JPG",
                "Curd", 98, 11f, 3.4f, 4.3f));

        addMeal(new Meal("https://images.heb.com/is/image/HEBGrocery/000032968",
                "Kefir", 139, 8 ,9 , 8));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void addMeal(Meal meal) {
        ContentValues cv = new ContentValues();
        cv.put("name", meal.name);
        cv.put("kkal_per_portion", meal.caloriesPerPortion);
        cv.put("proteins", meal.proteinAmount);
        cv.put("carbs", meal.carbAmount);
        cv.put("fats", meal.fatAmount);
        cv.put("image_url", meal.imgUrl);
        db.insert("meals", null, cv);
        cv.clear();
    }
}
