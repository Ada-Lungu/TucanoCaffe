package com.example.ada.tucanocaffe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ada on 8/20/16.
 */
class tucanoDatabaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "tucano_db";
    private static final int db_version = 2;

//    constructor of the SQLiteOpenHelper superclass
    tucanoDatabaseHelper(Context context){
        super(context, db_name, null, db_version);

    }

    public void onCreate(SQLiteDatabase db) {

        updateMyDatabase(db, 0, db_version);

    }

    private static void insertCoffee(SQLiteDatabase db, String name, String description, int resourceId) {
        //       object ContentValues holds key-values pairs of data
        ContentValues coffeeValues = new ContentValues();
        coffeeValues.put("Name", name);
        coffeeValues.put("Description", description);
        coffeeValues.put("ImageResourceId", resourceId);
        //        we insert these into the Coffee table
        db.insert("Coffee", null, coffeeValues);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

//      if the user does not have any db, it creates it=> calls onCreate
        if (oldVersion < 1){
            db.execSQL("CREATE TABLE Coffee( "
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT, "
                    + "Description TEXT, "
                    + "ImageResourceId INTEGER);");

            insertCoffee(db, "Latte", "A couple of expresso shots with steamed milk", R.drawable.latte);
            insertCoffee(db, "Cappuccino", "Espresso, hot milk and steamed milk foam", R.drawable.cappucino2);
            insertCoffee(db, "Espresso", "Highest quality beans roasted and brewed fresh", R.drawable.espresso);
            insertCoffee(db, "Machiatto", "Literally means stained milk. The name comes from the procedure through which the milk is stained by the addition of espresso", R.drawable.machiatto);


        }

//      if the user has the db, but the version is lower than current which is 2
        if (oldVersion < 2){
            db.execSQL("ALTER TABLE COFFEE ADD COLUMN FAVOURITE NUMERIC;");
        }


    }


}
