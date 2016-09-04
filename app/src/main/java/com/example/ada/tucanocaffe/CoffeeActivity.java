package com.example.ada.tucanocaffe;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class CoffeeActivity extends Activity {

    public static final String EXTRA_COFFEE_NO = "coffeeNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        // get id of coffee from intent + the coffee object with that id
        int coffeeNo = (Integer)getIntent().getExtras().get(EXTRA_COFFEE_NO);

//      create a cursor

        try{
//          create a hel[per object
            SQLiteOpenHelper tucanoDatabaseHelper = new tucanoDatabaseHelper(this);
            SQLiteDatabase db = tucanoDatabaseHelper.getReadableDatabase();
//          the query method returns a cursor object, from which we extract data to display in Activities
            Cursor cursor = db.query("Coffee",
                    new String[] {"Name", "Description", "ImageResourceId"},
                    "_id = ?",
                    new String[] {Integer.toString(coffeeNo)},
                    null, null, null
            );

//          navigate through the cursor for data
            if (cursor.moveToFirst()){

                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageId = cursor.getInt(2);

                // populate the drink image
                ImageView coffeeImageView = (ImageView) findViewById(R.id.image);
                coffeeImageView.setImageResource(imageId);
                coffeeImageView.setContentDescription(nameText);

                TextView coffeeName = (TextView) findViewById(R.id.name);
                coffeeName.setText(nameText);

//                TextView coffeePrice = (TextView) findViewById(R.id.price);
//                coffeePrice.setText(String.valueOf(coffeePrice);

                TextView coffeeDescrip = (TextView) findViewById(R.id.description);
                coffeeDescrip.setText(descriptionText);

//        Button sendMessage = (Button) findViewById(R.id.selectCoffee);

            }
            cursor.close();
            db.close();

        }catch (SQLiteException e){

            Toast toast = Toast.makeText(this, "DB UNavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void onSendOrder(View view){

        int coffeeNo = (Integer)getIntent().getExtras().get(EXTRA_COFFEE_NO);

        EditText clientMessageView = (EditText)findViewById(R.id.clientMessage);
        String clientMessage = clientMessageView.getText().toString();

        Spinner tableNumsArray = (Spinner) findViewById(R.id.tableNums); // gives me an array
        String tableNum = tableNumsArray.getSelectedItem().toString();

        Intent sendOrderIntent = new Intent(CoffeeActivity.this, OrderActivity.class);
        sendOrderIntent.putExtra("clientMessage", clientMessage);
        sendOrderIntent.putExtra("tableNumber", tableNum);
        sendOrderIntent.putExtra("coffeeId", Integer.toString(coffeeNo));
        startActivity(sendOrderIntent);


    }
//
//    //  when checkbox is selected, update the database
//    public void onFavoriteClicked(View view) {
//        int coffeeId = (Integer) getIntent().getExtras().get(EXTRA_COFFEE_NO);
//        new UpdateCoffeeTask().execute(coffeeId);
//    }
//
//    private class UpdateCoffeeTask extends AsyncTask<Integer, Void, Boolean> {
//
//        ContentValues coffeeValues;
//
//        protected void onPreExecute() {
//            CheckBox favoriteCoffee = (CheckBox) findViewById(R.id.favorite);
//            coffeeValues = new ContentValues();
//            coffeeValues.put("Favorite", favoriteCoffee.isChecked());
//
//        }
//
//        @Override
//        protected Boolean doInBackground(Integer... coffees) {
//
//            int coffeeId = coffees[0];
//            SQLiteOpenHelper tucanoDatabaseHelper = new tucanoDatabaseHelper(CoffeeActivity.this);
//
//            try {
//                SQLiteDatabase db = tucanoDatabaseHelper.getWritableDatabase();
//                db.update("Coffee", coffeeValues,
//                        "_id=?", new String[]{Integer.toString(coffeeId)}
//                );
//                db.close();
//                return true;
//            } catch (SQLiteException e) {
//                return false;
//            }
//        }
//
//        protected void onPostExecute(Boolean success){
//
//            if(!success){
//                Toast toast = Toast.makeText(CoffeeActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
//
//                toast.show();
//
//            }
//        }
//    }


    }
