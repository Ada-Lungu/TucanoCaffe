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

    }

    public void onSeeMenu(View view){

            Intent intent = new Intent (this, MenuOptionsActivity.class);
            startActivity(intent);

    }

}
