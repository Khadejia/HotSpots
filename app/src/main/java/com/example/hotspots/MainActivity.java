package com.example.hotspots;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    myRestaurantTableHelper myRestaurantTableHelper;
    String restaurantName, restaurantAddress, city, state, zipcode, entreeName, appetizerName,
            dessertName, restaurantId;
    double entreeRating, appetizerRating, dessertRating;
    EditText editTextRestaurantName, editTextRestaurantAddress, editTextCity, editTextState,
            editTextZipCode, editTextEntreeName, editTextAppetizerName, editTextDessertName;
    Button buttonSave, buttonRate, buttonDoRating;
    RatingBar entreeRatingBar,  appetizerRatingBar, dessertRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapWidgetsMainActivity();

    }


    public boolean getLocationDetails(EditText editTextRestaurantName, EditText editTextRestaurantAddress,
                                      EditText editTextCity, EditText editTextState, EditText editTextZipCode){

        if (!editTextRestaurantName.getText().toString().equals("") &&
                !editTextRestaurantAddress.getText().toString().equals("") ){

            restaurantName = editTextRestaurantName.getText().toString().toLowerCase();
            restaurantAddress = editTextRestaurantAddress.getText().toString().toLowerCase();
            city = editTextCity.getText().toString().toLowerCase();
            state = editTextState.getText().toString().toLowerCase();
            zipcode = editTextZipCode.getText().toString().toLowerCase();
            return true;
        }
        return false;
    }

    public void mapWidgetsMainActivity(){


        editTextRestaurantName = findViewById(R.id.editTextLocationName);
        editTextRestaurantAddress = findViewById(R.id.editTextStreetAddress);
        editTextCity = findViewById(R.id.editTextCity);
        editTextState = findViewById(R.id.editTextState);
        editTextZipCode = findViewById(R.id.editTextZipcode);
        buttonRate = findViewById(R.id.buttonRate);
        buttonSave = findViewById(R.id.buttonSaveLocationDetails);

        myRestaurantTableHelper = new myRestaurantTableHelper(this, null,null, 1);

    }

    public void mapWidgetsRatingLayout(){


        appetizerRatingBar = findViewById(R.id.ratingBarAppetizer);
        dessertRatingBar = findViewById(R.id.ratingBarDessert);
        entreeRatingBar = findViewById(R.id.ratingBarEntree);
        buttonDoRating = findViewById(R.id.buttonDoRating);
        editTextEntreeName = findViewById(R.id.editTextEntree);
        editTextAppetizerName = findViewById(R.id.editTextAppetizer);
        editTextDessertName = findViewById(R.id.editTextDessert);

    }

    public void changeLayoutToRatingLayout(View view){


        setContentView(R.layout.rating_layout);

        mapWidgetsRatingLayout();

    }


    public void doRating(View view){

        if (getLocationDetails(editTextRestaurantName, editTextRestaurantAddress, editTextCity,
                editTextState, editTextZipCode)){


            dessertRating = dessertRatingBar.getRating();
            appetizerRating = appetizerRatingBar.getRating();
            entreeRating = entreeRatingBar.getRating();
            entreeName = editTextEntreeName.getText().toString().toLowerCase();
            appetizerName = editTextAppetizerName.getText().toString().toLowerCase();
            dessertName = editTextDessertName.getText().toString().toLowerCase();
            restaurantId = myRestaurantTableHelper.getId(restaurantName,restaurantAddress);

            System.out.println(appetizerName);
            System.out.println(dessertName);
            System.out.println(entreeName);

            myRestaurantTableHelper.addDishToDB(entreeName, appetizerName, dessertName, entreeRating,
                    appetizerRating, dessertRating, restaurantId);

            setContentView(R.layout.activity_main);

            mapWidgetsMainActivity();



            Toast.makeText(getApplicationContext(),
                    "Your ratings have been saved to the database",
                    Toast.LENGTH_LONG).show();
        }


        else {
            Toast.makeText(getApplicationContext(),
                    "Please enter valid location information",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void saveLocation(View view){


        if(getLocationDetails(editTextRestaurantName, editTextRestaurantAddress, editTextCity,
                editTextState, editTextZipCode)){


            if(myRestaurantTableHelper.findHandler(restaurantName, restaurantAddress)) {


                myRestaurantTableHelper.updateHandler(restaurantName, restaurantAddress, city,
                        state, zipcode);

            } else {

                myRestaurantTableHelper.addToDB(restaurantName, restaurantAddress, city,
                        state, zipcode);

            }

            Toast.makeText(getApplicationContext(),
                    "You have successfully saved your location details",
                    Toast.LENGTH_LONG).show();


        } else {

            Toast.makeText(getApplicationContext(),
                    "You have not yet entered any location details, please try again",
                    Toast.LENGTH_LONG).show();

        }

    }


}