package com.example.hotspots;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDishTableHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restaurantRater.db";
    public static final String TABLE_NAME = "Dish";
    public static final String COLUMN_ID = "DishId";
    public static final String COLUMN_DISH_NAME = "Name";
    public static final String COLUMN_DISH_TYPE = "Type";
    public static final String COLUMN_DISH_RATING = "Rating";
    public static final String COLUMN_RESTAURANT_ID = "RestaurantID";



    public myDishTableHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DISH_NAME + " TEXT, " +
                COLUMN_DISH_TYPE + " TEXT, " + COLUMN_DISH_RATING + " REAL, "
                + COLUMN_RESTAURANT_ID + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addToDB( String entreeName, String appetizerName, String dessertName,
                         double entreeRating, double appetizerRating, double dessertRating,
                         String restaurantID){


        ContentValues entreeValues = new ContentValues();
        ContentValues appetizerValues = new ContentValues();
        ContentValues dessertValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();


        entreeValues.put(COLUMN_DISH_NAME, entreeName);
        entreeValues.put(COLUMN_DISH_TYPE, "entree");
        entreeValues.put(COLUMN_DISH_RATING, entreeRating);
        entreeValues.put(COLUMN_RESTAURANT_ID, restaurantID);

        db.insert(TABLE_NAME, null, entreeValues);

        appetizerValues.put(COLUMN_DISH_NAME, appetizerName);
        appetizerValues.put(COLUMN_DISH_TYPE, "appetizer");
        appetizerValues.put(COLUMN_DISH_RATING, appetizerRating);
        appetizerValues.put(COLUMN_RESTAURANT_ID, restaurantID);

        db.insert(TABLE_NAME, null, appetizerValues);

        entreeValues.put(COLUMN_DISH_NAME, dessertName);
        entreeValues.put(COLUMN_DISH_TYPE, "dessert");
        entreeValues.put(COLUMN_DISH_RATING, dessertRating);
        entreeValues.put(COLUMN_RESTAURANT_ID, restaurantID);


        db.insert(TABLE_NAME, null, dessertValues);

        db.close();
    }




}