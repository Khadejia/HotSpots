package com.example.hotspots;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class myRestaurantTableHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restaurantRater.db";
    public static final String RESTAURANT_TABLE_NAME = "restaurants";
    public static final String COLUMN_ID = "RestaurantId";
    public static final String COLUMN_RESTAURANT_NAME = "Name";
    public static final String COLUMN_STREET_ADDRESS = "StreetAddress";
    public static final String COLUMN_CITY = "City";
    public static final String COLUMN_STATE = "State";
    public static final String COLUMN_ZIPCODE = "ZipCode";


    public static final String DISH_TABLE_NAME = "Dish";
    public static final String COLUMN_ID_DISH_TABLE = "DishId";
    public static final String COLUMN_DISH_NAME = "Name";
    public static final String COLUMN_DISH_TYPE = "Type";
    public static final String COLUMN_DISH_RATING = "Rating";
    public static final String COLUMN_RESTAURANT_ID = "RestaurantID";

    public myRestaurantTableHelper(@Nullable Context context, @Nullable String name,
                                   @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RESTAURANT_TABLE_NAME + " ( " + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RESTAURANT_NAME + " TEXT, " +
                COLUMN_STREET_ADDRESS + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_STATE + " TEXT, " +
                COLUMN_ZIPCODE + " TEXT)";

        db.execSQL(CREATE_RESTAURANT_TABLE);


        String CREATE_DISH_TABLE = "CREATE TABLE " + DISH_TABLE_NAME + " ( " + COLUMN_ID_DISH_TABLE +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DISH_NAME + " TEXT, " +
                COLUMN_DISH_TYPE + " TEXT, " + COLUMN_DISH_RATING + " REAL, "
                + COLUMN_RESTAURANT_ID + " TEXT)";
        db.execSQL(CREATE_DISH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addToDB(String restaurantName, String streetAddress, String city, String state,
                        String zipcode) {


        ContentValues values = new ContentValues();


        values.put(COLUMN_RESTAURANT_NAME, restaurantName);
        values.put(COLUMN_STREET_ADDRESS, streetAddress);
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_ZIPCODE, zipcode);
        values.put(COLUMN_STATE, state);

        SQLiteDatabase db = this.getWritableDatabase();


        db.insert(RESTAURANT_TABLE_NAME, null, values);
        db.close();
    }


    public void updateHandler(String restaurantName, String streetAddress, String city, String state,
                              String zipcode) {


        ContentValues values = new ContentValues();


        values.put(COLUMN_RESTAURANT_NAME, restaurantName);
        values.put(COLUMN_STREET_ADDRESS, streetAddress);
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_ZIPCODE, zipcode);
        values.put(COLUMN_STATE, state);


        SQLiteDatabase db = this.getWritableDatabase();


        db.update(RESTAURANT_TABLE_NAME, values, COLUMN_RESTAURANT_NAME + " = '" + restaurantName +
                "' AND " + COLUMN_STREET_ADDRESS + " = '" + streetAddress + "'", null);

        db.close();
    }


    public boolean findHandler(String restaurantName, String address) {

        String query = "Select * FROM " + RESTAURANT_TABLE_NAME + " WHERE " + COLUMN_RESTAURANT_NAME + " = " + "'"
                + restaurantName + "' AND " + COLUMN_STREET_ADDRESS + " = '" + address + "'";

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {

            cursor.close();

            db.close();
            return true;
        }

        return false;
    }

    public String getId(String restaurantName, String address) {

        String id = null;

        String query = "Select RestaurantId FROM " + RESTAURANT_TABLE_NAME + " WHERE " + COLUMN_RESTAURANT_NAME + " = " + "'"
                + restaurantName + "' AND " + COLUMN_STREET_ADDRESS + " = '" + address + "'";

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery(query, null);



        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            id = cursor.getString(0);

            cursor.close();

            db.close();
        }
        return id;
    }


    public void addDishToDB(String entreeName, String appetizerName, String dessertName,
                            double entreeRating, double appetizerRating, double dessertRating,
                            String restaurantID) {


        ContentValues entreeValues = new ContentValues();
        ContentValues appetizerValues = new ContentValues();
        ContentValues dessertValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();


        entreeValues.put(COLUMN_DISH_NAME, entreeName);
        entreeValues.put(COLUMN_DISH_TYPE, "entree");
        entreeValues.put(COLUMN_DISH_RATING, entreeRating);
        entreeValues.put(COLUMN_RESTAURANT_ID, restaurantID);

        db.insert(DISH_TABLE_NAME, null, entreeValues);

        appetizerValues.put(COLUMN_DISH_NAME, appetizerName);
        appetizerValues.put(COLUMN_DISH_TYPE, "appetizer");
        appetizerValues.put(COLUMN_DISH_RATING, appetizerRating);
        appetizerValues.put(COLUMN_RESTAURANT_ID, restaurantID);

        db.insert(DISH_TABLE_NAME, null, appetizerValues);

        dessertValues.put(COLUMN_DISH_NAME, dessertName);
        dessertValues.put(COLUMN_DISH_TYPE, "dessert");
        dessertValues.put(COLUMN_DISH_RATING, dessertRating);
        dessertValues.put(COLUMN_RESTAURANT_ID, restaurantID);

        //inserting into the table and closing the db
        db.insert(DISH_TABLE_NAME, null, dessertValues);

        db.close();
    }
}


