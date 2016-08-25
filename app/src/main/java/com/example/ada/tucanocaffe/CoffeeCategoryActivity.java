package com.example.ada.tucanocaffe;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by ada on 8/14/16.
 */
public class CoffeeCategoryActivity extends ListActivity{

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView coffeeCategoryListView = getListView();

        try{

            SQLiteOpenHelper helperDb = new tucanoDatabaseHelper(this);
            db = helperDb.getReadableDatabase();
            cursor = db.query("Coffee",
                                    new String[] {"_id", "Name"},
                                    null, null, null, null, null
            );

            CursorAdapter coffeeListAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"Name"},
                    new int[]{android.R.id.text1},
                    0);



        coffeeCategoryListView.setAdapter(coffeeListAdapter);

        }catch(SQLiteException e){

            Toast toast = Toast.makeText(this, "DB unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void onListItemClick(ListView listView, View itemView, int position, long id){

        Intent intent = new Intent(CoffeeCategoryActivity.this, CoffeeActivity.class);
        intent.putExtra(CoffeeActivity.EXTRA_COFFEE_NO, (int) id);
        startActivity(intent);

    }




}
