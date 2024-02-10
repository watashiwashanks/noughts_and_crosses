package com.example.cross2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextInputEditText login, password;
    TextView statusText;

    public class User {
        String userName;
        String password;
        String avatar;
        String userId;
        User(String userName,String password,String avatar,String userId){
            this.userName = userName;
            this.password = password;
            this.avatar = avatar;
            this.userId = userId;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.start_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StartGame.class);
                startActivity(intent);
            }
        });
        Button btn = findViewById(R.id.start_btn);
        login = findViewById(getResources().getIdentifier("login", "id", getPackageName()));
        password = findViewById(getResources().getIdentifier("password", "id", getPackageName()));
        statusText = findViewById(getResources().getIdentifier("statusText", "id", getPackageName()));



        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String first = login.getText().toString();
            String second = password.getText().toString();
            if (DB.users.containsKey(first) && DB.users.get(first).equals(second)) {
                DB.currentUser = first;
                statusText.setText("Успішно");
                statusText.setTextColor(Color.GREEN);
                Intent intent = new Intent(MainActivity.this, StartGame.class);
                startActivity(intent);
            } else {
                statusText.setText("Користувача не знайдено");
                statusText.setTextColor(Color.RED);
            }
                            }

        });
    }
}

