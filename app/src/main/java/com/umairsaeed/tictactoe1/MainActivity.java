package com.umairsaeed.tictactoe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    AppCompatButton gameStart;
    AppCompatEditText player_1, player_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent gotoGameLayout = new Intent(this, GameLayout.class);

        // get all id's
        player_1 = findViewById(R.id.edt_player1);
        player_2 = findViewById(R.id.edt_player2);
        gameStart = findViewById(R.id.btn_gameStart);

        gameStart.setOnClickListener(view -> {
            String p1 = Objects.requireNonNull(player_1.getText()).toString().trim();
            String p2 = Objects.requireNonNull(player_2.getText()).toString().trim();

            if(p1.length() < 2 || p2.length() < 2){
                if(p1.length() < 2){
                    player_1.requestFocus();
                }else{
                    player_2.requestFocus();
                }
                Toast.makeText(MainActivity.this, "Please Enter minimum 2 char players names", Toast.LENGTH_LONG).show();
            } else if (p1.length() <= 6 && p2.length() <= 6) {
                if(!p1.equalsIgnoreCase(p2)){
                    gotoGameLayout.putExtra("xName", p1);
                    gotoGameLayout.putExtra("oName", p2);
                    startActivity(gotoGameLayout);
                }else{
                    Toast.makeText(MainActivity.this, "Both Names are Same", Toast.LENGTH_SHORT).show();
                }

            }else {
                if (p1.length() > 6){
                    player_1.requestFocus();
                }else{
                    player_2.requestFocus();
                }
                Toast.makeText(MainActivity.this, "Maximum 5 characters of name is allowed", Toast.LENGTH_LONG).show();
            }


        });
    }

}