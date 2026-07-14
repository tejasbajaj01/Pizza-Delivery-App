package com.example.pixxa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AddressActivity extends AppCompatActivity {

    TextInputEditText houseInput;
    TextInputEditText streetInput;
    TextInputEditText cityInput;
    TextInputEditText stateInput;
    TextInputEditText pincodeInput;

    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        houseInput = findViewById(R.id.houseInput);
        streetInput = findViewById(R.id.streetInput);
        cityInput = findViewById(R.id.cityInput);
        stateInput = findViewById(R.id.stateInput);
        pincodeInput = findViewById(R.id.pincodeInput);

        saveBtn = findViewById(R.id.saveAddressBtn);

        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);

        // Load saved address
        String savedAddress = sp.getString("address", "");

        if (!savedAddress.isEmpty()) {

            String[] parts = savedAddress.split(",");

            if (parts.length >= 5) {
                houseInput.setText(parts[0].trim());
                streetInput.setText(parts[1].trim());
                cityInput.setText(parts[2].trim());
                stateInput.setText(parts[3].trim());

                // Remove "-" if present
                pincodeInput.setText(parts[4].replace("-", "").trim());
            }
        }

        saveBtn.setOnClickListener(v -> {

            String house = houseInput.getText().toString().trim();
            String street = streetInput.getText().toString().trim();
            String city = cityInput.getText().toString().trim();
            String state = stateInput.getText().toString().trim();
            String pincode = pincodeInput.getText().toString().trim();

            if (house.isEmpty() ||
                    street.isEmpty() ||
                    city.isEmpty() ||
                    state.isEmpty() ||
                    pincode.isEmpty()) {

                Toast.makeText(
                        AddressActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            String fullAddress =
                    house + ", " +
                            street + ", " +
                            city + ", " +
                            state + ", " +
                            pincode;

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("address", fullAddress);
            editor.apply();

            Toast.makeText(
                    AddressActivity.this,
                    "Address Saved Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        });
    }
}