package com.example.pixxa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import com.example.pixxa.database.DBHelper;
import com.example.pixxa.model.User;

public class Registerpage extends AppCompatActivity {

    EditText fullname, username, phone, email, pass;
    Button registerbtn;
    TextView loginTxt;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registerpage);



        fullname = findViewById(R.id.Fullname);
        username = findViewById(R.id.Username);
        phone = findViewById(R.id.Phonenumber);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.Pass);
        registerbtn = findViewById(R.id.Registerbtn);
        loginTxt = findViewById(R.id.loginTxt);

        dbHelper = new DBHelper(this);

        loginTxt.setOnClickListener(v -> {
            startActivity(new Intent(Registerpage.this, MainActivity.class));
            finish();
        });

        registerbtn.setOnClickListener(v -> {

            String name = fullname.getText().toString();
            String uname = username.getText().toString();
            String phoneNo = phone.getText().toString();
            String emailId = email.getText().toString();
            String password = pass.getText().toString();

            if (name.isEmpty() ||
                    uname.isEmpty() ||
                    phoneNo.isEmpty() ||
                    emailId.isEmpty() ||
                    password.isEmpty()) {

                Toast.makeText(
                        Registerpage.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            User user = new User(
                    name,
                    uname,
                    phoneNo,
                    emailId,
                    password
            );

            long result = dbHelper.insertUser(user);

            if (result > 0) {

                Toast.makeText(
                        Registerpage.this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                ).show();

                startActivity(
                        new Intent(
                                Registerpage.this,
                                MainActivity.class
                        )
                );

                finish();

            } else {

                Toast.makeText(
                        Registerpage.this,
                        "Registration Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}