package com.umairsaeed.tictactoe1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainManue extends AppCompatActivity {
    AppCompatButton one_player, two_player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manue);

        // hide title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        one_player = findViewById(R.id.btn_oneplayer);
        two_player = findViewById(R.id.btn_twoplayer);

        two_player.setOnClickListener(view -> {
            Intent gotoTwoPlayer = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(gotoTwoPlayer);
        });

        one_player.setOnClickListener(view -> {
            Intent gotoOnePlayer = new Intent(getApplicationContext(), PlayerOneName.class);
            startActivity(gotoOnePlayer);
        });


    }
}