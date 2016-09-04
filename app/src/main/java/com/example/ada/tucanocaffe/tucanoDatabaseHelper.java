package com.example.ada.tucanocaffe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ada on 8/20/16.
 */
class tucanoDatabaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "tucano_db";
    private static final int db_version = 4;

    //    constructor of the SQLiteOpenHelper superclass
    tucanoDatabaseHelper(Context context){
        super(context, db_name, null, db_version);
        Log.v("INFO:", "in constructor of tucando db helper");

    }

    public void onCreate(SQLiteDatabase db) {
        Log.v("INFO:", "in on create...");
    //  updateMyDatabase(db, 0, db_version);
        createDB(db);
    }

    private static void insertProduct(SQLiteDatabase db, String name, String description, int resourceId, int category) {
        //       object ContentValues holds key-values pairs of data
        ContentValues coffeeValues = new ContentValues();
        coffeeValues.put("Name", name);
        coffeeValues.put("Description", description);
        coffeeValues.put("ImageResourceId", resourceId);
        coffeeValues.put("Category", category);
        //        we insert these into the Coffee table
        db.insert("Product", null, coffeeValues);

    }


    private static void insertOrder(SQLiteDatabase db, int tableNo, String coffeeName, String clientMessage){
        ContentValues orderValues = new ContentValues();
        orderValues.put("tableNo",tableNo);
        orderValues.put("coffeeName", coffeeName);
        orderValues.put("clientMessage", clientMessage);
        //        we insert these into the Coffee table
        db.insert("Orders", null, orderValues);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("INFO:", "in upgrade....");
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void createDB(SQLiteDatabase db) {
//      if the user does not have any db, it creates it=> calls onCreate
        Log.v("INFO:", "in create table product");
            db.execSQL("CREATE TABLE Product( "
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT, "
                    + "Description TEXT, "
                    + "ImageResourceId INTEGER, "
                    + "Category INTEGER);");

            insertProduct(db, "Latte", "A couple of expresso shots with steamed milk", R.drawable.latte, Product.COFFEE_CATEGORY);
            insertProduct(db, "Cappuccino", "Espresso, hot milk and steamed milk foam", R.drawable.cappucino2, Product.COFFEE_CATEGORY);
            insertProduct(db, "Espresso", "Highest quality beans roasted and brewed fresh", R.drawable.espresso, Product.COFFEE_CATEGORY);
            insertProduct(db, "Machiatto", "Literally means stained milk. The name comes from the procedure through which the milk is stained by the addition of espresso", R.drawable.machiatto, Product.COFFEE_CATEGORY);
            insertProduct(db, "Brownie", "Delicious and fluffy!", R.drawable.machiatto, Product.SWEETS_CATEGORY);



//      if the user has the db, but the version is lower than current which is 2
            Log.v("INFO:", "in create table order");
            db.execSQL("CREATE TABLE Orders( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TableNo INTEGER, "
                + "CoffeeName TEXT, "
                + "ClientMessage TEXT);");

            insertOrder(db, 1, "Cappucino", "Without sugar please!");
            insertOrder(db, 2, "Latte", "Without sugar please!");

    }

}