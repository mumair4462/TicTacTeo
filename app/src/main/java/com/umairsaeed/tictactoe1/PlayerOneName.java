package com.umairsaeed.tictactoe1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class PlayerOneName extends AppCompatActivity {

    RadioGroup radioGroup, radioMode;
    RadioButton radio_x, radio_o, radioButton, radioBtn, radio_easy, radio_hard;

    AppCompatEditText playerName;
    AppCompatButton gameStart;

    String playerIcon = "x", gameMode = "easy";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_one_name);

        //hide title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        radioGroup = findViewById(R.id.radio_group);
        radio_x = findViewById(R.id.radio_x);
        radio_o = findViewById(R.id.radio_o);

        radioMode = findViewById(R.id.radio_mode);
        radio_easy = findViewById(R.id.radio_easy);
        radio_hard = findViewById(R.id.radio_hard);

        playerName = findViewById(R.id.edt_palyername);
        gameStart = findViewById(R.id.btn_gameStart);

        gameStart.setOnClickListener(view -> {
            String name = Objects.requireNonNull(playerName.getText()).toString().trim();

            if(name.length() >= 2){
                Intent gotoGame = new Intent(getApplicationContext(), GameOnePlayerLayout.class);
                gotoGame.putExtra("name", name);
                gotoGame.putExtra("icon", playerIcon);
                gotoGame.putExtra("mode", gameMode);
                startActivity(gotoGame);
            }else {
                Toast.makeText(PlayerOneName.this, "Minimum length is 2", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void clickRadio(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
        if (radioButton.equals(radio_x)){
            playerIcon = "x";
        }else{
            playerIcon = "o";
        }
    }


    public void onClickMode(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioBtn = findViewById(radioid);
        if (radioBtn.equals(radio_easy)){
            gameMode = "easy";
        }else{
            gameMode = "hard";
        }
    }
}