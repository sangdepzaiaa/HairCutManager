package com.god.haircutmanager.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.god.haircutmanager.Model.User;
import com.god.haircutmanager.R;

public class AddUserActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText roleEditText;
    private Button saveButton;

    private long userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        roleEditText = findViewById(R.id.roleEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(view-> {
            saveUser();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("user_id")) {
            userId = extras.getLong("user_id");
            loadUserData();
        }
    }

    private void loadUserData() {
        if (userId != -1) {
            User user = User.findById(User.class, userId);
            if (user != null) {
                usernameEditText.setText(user.getUsername());
                passwordEditText.setText(user.getPassword());
                roleEditText.setText(user.getRole());
            }
        }
    }

    private void saveUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String role = roleEditText.getText().toString();

        if (!username.isEmpty() && !password.isEmpty() && !role.isEmpty()) {
            User user;

            if (userId != -1) {
                user = User.findById(User.class, userId);
            } else {
                user = new User();
            }

            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.save();

            Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
