package com.example.cross2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FinishedGame extends AppCompatActivity {
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game);

        String winner = getIntent().getStringExtra("winner");
        TextView text = findViewById(R.id.textView3);
        text.setText(winner);
        if(winner.equals("DRAW")) {
            findViewById(R.id.ctL).setBackgroundColor(0xfcba03);
        }

        btn = findViewById(R.id.btn_restart_game);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FinishedGame.this, StartGame.class);
                startActivity(intent1);

            }
        });

        if(DB.results.containsKey(DB.currentUser)) {
            List<String> ls = DB.results.get(DB.currentUser);
            ls.add(winner);
            DB.results.put(DB.currentUser, ls);
        } else {
            List<String> ls = new ArrayList<>();
            ls.add(winner);
            DB.results.put(DB.currentUser, ls);
        }




    }

}