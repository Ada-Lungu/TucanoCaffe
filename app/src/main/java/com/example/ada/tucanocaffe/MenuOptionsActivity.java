package com.example.ada.tucanocaffe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MenuOptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_options);

        // create an OnItemClickListener
        AdapterView.OnItemClickListener productClickListener = new AdapterView.OnItemClickListener()
                // anonymous class that implements the OnItemClickListener() interface
        {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id){
                Intent intent = new Intent(MenuOptionsActivity.this, AllProductsInCategoryActivity.class);
                String selectedText = listView.getItemAtPosition(position).toString();

                int category = 0;
                if (selectedText.equals("Coffees")) {
                    category = Product.COFFEE_CATEGORY;
                }
                if (selectedText.equals("Sweets")) {
                    category = Product.SWEETS_CATEGORY;

                }
                intent.putExtra(AllProductsInCategoryActivity.EXTRA_CATEGORY, category);
                startActivity(intent);

            }
        };

        // attach the listener to the ListView
        ListView productsListMenu = (ListView) findViewById(R.id.listProductsMenu);
        productsListMenu.setOnItemClickListener(productClickListener);

    }


    public void onBackToMain(View view){

        Intent intent = new Intent(this, TopLevelActivity.class);
        startActivity(intent);


    }


}
