package com.example.cross2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ResultsScreen extends AppCompatActivity {

    TextView tv;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        tv = findViewById(R.id.textView2);


        if (DB.results.containsKey(DB.currentUser)) {
            List<String> resultsList = DB.results.get(DB.currentUser);
            for (int i = 0; i < resultsList.size(); i++) {
                tv.setText(tv.getText() + resultsList.get(i) + "\n");

            }
        } else {

            tv.setText("Не знайдно користувача");
        }
        btn = findViewById(R.id.btn_return);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResultsScreen.this, StartGame.class);
                startActivity(intent1);

            }

        });

    }
}

