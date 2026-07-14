package com.example.pixxa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pixxa.R;

public class OrderTrackingActivity extends AppCompatActivity {

    TextView orderIdText, addressText, statusPlaced, statusPreparing, statusDelivery, statusDelivered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);

        orderIdText = findViewById(R.id.orderIdText);
        addressText = findViewById(R.id.addressText);

        statusPlaced = findViewById(R.id.statusPlaced);
        statusPreparing = findViewById(R.id.statusPreparing);
        statusDelivery = findViewById(R.id.statusDelivery);
        statusDelivered = findViewById(R.id.statusDelivered);

        // SAFE INTENT READ
        String address = getIntent().getStringExtra("address");
        String orderId = getIntent().getStringExtra("order_id"); // FIXED KEY
        String status = getIntent().getStringExtra("status");

        if (address == null) address = "Not Available";
        if (orderId == null) orderId = "0";
        if (status == null) status = "Placed";

        addressText.setText("Delivering to: " + address);
        orderIdText.setText("Order #" + orderId);

        updateStatus(status);
    }

    private void updateStatus(String status) {

        resetAll();

        if (status == null) return;

        switch (status) {

            case "Placed":
                statusPlaced.setTextColor(getColor(R.color.black));
                break;

            case "Preparing":
                statusPlaced.setTextColor(getColor(R.color.green));
                statusPreparing.setTextColor(getColor(R.color.black));
                break;

            case "OnTheWay":
                statusPlaced.setTextColor(getColor(R.color.green));
                statusPreparing.setTextColor(getColor(R.color.green));
                statusDelivery.setTextColor(getColor(R.color.black));
                break;

            case "Delivered":
                statusPlaced.setTextColor(getColor(R.color.green));
                statusPreparing.setTextColor(getColor(R.color.green));
                statusDelivery.setTextColor(getColor(R.color.green));
                statusDelivered.setTextColor(getColor(R.color.green));
                break;
        }
    }

    private void resetAll() {
        statusPlaced.setTextColor(getColor(R.color.gray));
        statusPreparing.setTextColor(getColor(R.color.gray));
        statusDelivery.setTextColor(getColor(R.color.gray));
        statusDelivered.setTextColor(getColor(R.color.gray));
    }
}