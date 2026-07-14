package com.example.pixxa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pixxa.database.DBHelper;
import com.example.pixxa.model.User;

public class ProfileFragment extends Fragment {

    TextView txtName, txtUsername, txtEmail, txtPhone;
    Button logoutBtn;

    DBHelper dbHelper;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtName = view.findViewById(R.id.txtName);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);
        logoutBtn = view.findViewById(R.id.logoutBtn);

        dbHelper = new DBHelper(requireContext());

        SharedPreferences sp = requireContext()
                .getSharedPreferences("UserData", requireContext().MODE_PRIVATE);

        String username = sp.getString("username", "");

        User user = dbHelper.getUser(username);

        if (user != null) {
            txtName.setText(user.getName());
            txtUsername.setText(user.getUsername());
            txtEmail.setText(user.getEmail());
            txtPhone.setText(user.getPhone());
        }

        logoutBtn.setOnClickListener(v -> {

            sp.edit().clear().apply();

            Intent intent = new Intent(requireContext(), MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);

            requireActivity().finish();

        });

        return view;
    }
}