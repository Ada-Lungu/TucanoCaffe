package com.example.ada.tucanocaffe;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class OrdersPageActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView orderListView = getListView();
        // create adapter to bridge data from Order class (Order Array Object data) to a view element of this Activity
        ArrayAdapter<Order> orderList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Order.orders);

        // ListView orderListView = (ListView)findViewById(R.id.orderList);

        orderListView.setAdapter(orderList);

    }

    // 1. create an onItemClickListener and attach it to the orderListView
    // 2. use a ListActivity instead of Activity ==> this implements the layout and ListView by default
    // + implements already a listener ==> we need to implement onLIstItemClick method instead

    public void onListItemClick(ListView listView, View itemView, int position, long id){
//        String idStr = String.valueOf(id);
        Intent intent = new Intent(OrdersPageActivity.this, OrderActivity.class);
        intent.putExtra(OrderActivity.ORDER_ID, (int) id);
        startActivity(intent);
    }

}
