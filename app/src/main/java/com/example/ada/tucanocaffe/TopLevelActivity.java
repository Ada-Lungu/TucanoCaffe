package com.example.ada.tucanocaffe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class TopLevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

    // create an OnItemClickListener
        AdapterView.OnItemClickListener productClickListener = new AdapterView.OnItemClickListener()
    // anonymous class that implements the OnItemClickListener() interface
        {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id){
                if (position == 0){
                    Intent intent = new Intent(TopLevelActivity.this, CoffeeCategoryActivity.class);
                    startActivity(intent);
                }

            }
        };

    // attach the listener to the ListView
        ListView productsListMenu = (ListView) findViewById(R.id.listProductsMenu);
        productsListMenu.setOnItemClickListener(productClickListener);

    }

}


//            class OnProductClickListener implements AdapterView.OnItemClickListener {
//
//                public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
//                    if (position == 0) {
//                        Intent intent = new Intent(TopLevelActivity.this, CoffeeCategoryActivity.class);
//                        startActivity(intent);
//                    }
//
//                }
//            }
//
//        OnProductClickListener productClickListener = new OnProductClickListener();