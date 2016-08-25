package com.example.ada.tucanocaffe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class OrderActivity extends Activity {

    public static final String ORDER_ID = "orderId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        int orderId = (Integer)getIntent().getExtras().get(ORDER_ID);
        Order order = Order.orders[orderId];


        Intent getOrderInfo = getIntent();
//        String tableNum = (String) getOrderInfo.getExtras().get("tableNumber");
//        String clientMessage = (String) getOrderInfo.getExtras().get("clientMessage");

        TextView tableNumView = (TextView) findViewById(R.id.tableNum);
        tableNumView.setText(order.getTableNum());
//        tableNumView.setText(tableNum);

        TextView clientMessageView = (TextView) findViewById(R.id.clientMessage);
        clientMessageView.setText(order.getClientMessage());
//        clientMessageView.setText(clientMessage);


//        Intent sendOrderInfoToClass = new Intent(OrderActivity.this, Order.class);
//        sendOrderInfoToClass.putExtra("tableNum", tableNum);
//        sendOrderInfoToClass.putExtra("clientMessage", clientMessage);

    }

}
