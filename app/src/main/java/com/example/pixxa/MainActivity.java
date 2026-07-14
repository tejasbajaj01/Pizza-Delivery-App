package com.example.pixxa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pixxa.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText User, Pass;
    Button Loginbtn;
    TextView registertxt;

    DBHelper dbHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);

        if (sp.getString("username", null) != null) {

            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom);
            return insets;
        });

        User = findViewById(R.id.User);
        Pass = findViewById(R.id.Pass);
        Loginbtn = findViewById(R.id.Loginbtn);
        registertxt = findViewById(R.id.registertxt);

        dbHelper = new DBHelper(this);

        Loginbtn.setOnClickListener(v -> {

            String username = User.getText().toString().trim();
            String password = Pass.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            boolean check = dbHelper.checkLogin(username, password);

            if (check) {

                sp.edit()
                        .putString("username", username)
                        .apply();

                Toast.makeText(
                        MainActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        MainActivity.this,
                        HomeActivity.class
                );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Invalid Username or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        registertxt.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    Registerpage.class
            );

            startActivity(intent);

        });

    }
}