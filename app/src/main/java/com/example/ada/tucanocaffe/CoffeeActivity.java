package com.example.ada.tucanocaffe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


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

        EditText clientMessageView = (EditText)findViewById(R.id.clientMessage);
        String clientMessage = clientMessageView.getText().toString();

        Spinner tableNumsArray = (Spinner) findViewById(R.id.tableNums); // gives me an array
        String tableNum = tableNumsArray.getSelectedItem().toString();

        Intent sendOrderIntent = new Intent(CoffeeActivity.this, OrderActivity.class);
        sendOrderIntent.putExtra("clientMessage", clientMessage);
        sendOrderIntent.putExtra("tableNumber", tableNum);
        startActivity(sendOrderIntent);


    }


}
