package com.umairsaeed.tictactoe1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

public class GameLayout extends AppCompatActivity {

    private static final int HORIZENTAL_LINE = R.drawable.line_horizental;
    private static final int VERTICAL_LINE = R.drawable.line_vertical;
    private static final int CROSS_LINE = R.drawable.line_cross;
    private static final int REVERSE_CROSS_LINE = R.drawable.line_reverse_cross;
    private static final int DEFAULT_BACKGROUND = R.drawable.unactive_player;
    AppCompatTextView txtP1, txtP2, p1_counter, p2_counter;
    Integer click_counter = 0, playerOneWinCounter = 0, playerTwoWinCounter = 0;
    Boolean isPlayerOne = true, gameOver = false;

    AppCompatImageButton box1, box2, box3, box4, box5, box6, box7, box8, box9;

    LinearLayoutCompat container_player1;
    LinearLayoutCompat container_player2;

    String playerNameOne, playerNameTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent getData = getIntent();
        playerNameOne = getData.getStringExtra("xName");
        playerNameTwo = getData.getStringExtra("oName");

        // get id's
        txtP1 = findViewById(R.id.txt_p1);
        txtP2 = findViewById(R.id.txt_p2);
        // set name of players
        txtP1.setText(playerNameOne);
        txtP2.setText(playerNameTwo);
        init();
    }



    public void onClicked(View view) {
        AppCompatImageButton btnCurrent = (AppCompatImageButton) view;


        if( gameOver.equals(false) ){
            final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click_sound);
            mediaPlayer.start();
            if(btnCurrent.getTag().toString().equals("c0")){

                if(isPlayerOne){
                    btnCurrent.setImageResource(R.drawable.x);
                    btnCurrent.setTag("c1");
                    container_player2.setBackgroundResource(R.drawable.active_player);
                    container_player1.setBackgroundResource(R.drawable.unactive_player);
                }else {
                    btnCurrent.setImageResource(R.drawable.o);
                    btnCurrent.setTag("c2");
                    container_player1.setBackgroundResource(R.drawable.active_player);
                    container_player2.setBackgroundResource(R.drawable.unactive_player);
                }
                isPlayerOne = !isPlayerOne;
                click_counter++;
            }

            String tag1 = box1.getTag().toString();
            String tag2 = box2.getTag().toString();
            String tag3 = box3.getTag().toString();
            String tag4 = box4.getTag().toString();
            String tag5 = box5.getTag().toString();
            String tag6 = box6.getTag().toString();
            String tag7 = box7.getTag().toString();
            String tag8 = box8.getTag().toString();
            String tag9 = box9.getTag().toString();

            if(tag1.equals(tag2) && tag1.equals(tag3) && !tag1.equals("c0")){
                drawLine(box1,box2,box3,HORIZENTAL_LINE);
                win();
            }else if (tag4.equals(tag5) && tag4.equals(tag6) && !tag4.equals("c0")){
                drawLine(box4,box5,box6,HORIZENTAL_LINE);
                win();
            }else if (tag7.equals(tag8) && tag7.equals(tag9) && !tag7.equals("c0")){
                drawLine(box7,box8,box9,HORIZENTAL_LINE);
                win();
            } else if (tag1.equals(tag5) && tag1.equals(tag9) && !tag1.equals("c0")) {
                drawLine(box1,box5,box9,CROSS_LINE);
                win();
            }else if (tag7.equals(tag5) && tag7.equals(tag3) && !tag7.equals("c0")) {
                drawLine(box7,box5,box3,REVERSE_CROSS_LINE);
                win();
            }else if (tag1.equals(tag4) && tag1.equals(tag7) && !tag1.equals("c0")) {
                drawLine(box1,box4,box7,VERTICAL_LINE);
                win();
            }else if (tag2.equals(tag5) && tag2.equals(tag8) && !tag2.equals("c0")) {
                drawLine(box2,box5,box8,VERTICAL_LINE);
                win();
            }else if (tag3.equals(tag6) && tag3.equals(tag9) && !tag3.equals("c0")) {
                drawLine(box3,box6,box9,VERTICAL_LINE);
                win();
            }else if (click_counter == 9){
                draw();
            }

        }

    }

    public void init(){

        container_player1 = findViewById(R.id.container_player1);
        container_player2 = findViewById(R.id.container_player2);

        p1_counter = findViewById(R.id.txt_p1_count);
        p2_counter =  findViewById(R.id.txt_p2_count);


        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        box4 = findViewById(R.id.box4);
        box5 = findViewById(R.id.box5);
        box6 = findViewById(R.id.box6);
        box7 = findViewById(R.id.box7);
        box8 = findViewById(R.id.box8);
        box9 = findViewById(R.id.box9);
    }

    public void restart(){

        gameOver = false;
        click_counter = 0;

        box1.setImageResource(0);
        box2.setImageResource(0);
        box3.setImageResource(0);
        box4.setImageResource(0);
        box5.setImageResource(0);
        box6.setImageResource(0);
        box7.setImageResource(0);
        box8.setImageResource(0);
        box9.setImageResource(0);
        box1.setTag("c0");
        box2.setTag("c0");
        box3.setTag("c0");
        box4.setTag("c0");
        box5.setTag("c0");
        box6.setTag("c0");
        box7.setTag("c0");
        box8.setTag("c0");
        box9.setTag("c0");

        defaultBackground(box1);
        defaultBackground(box2);
        defaultBackground(box3);
        defaultBackground(box4);
        defaultBackground(box5);
        defaultBackground(box6);
        defaultBackground(box7);
        defaultBackground(box8);
        defaultBackground(box9);

    }

    @SuppressLint("SetTextI18n")
    public void win(){
        gameOver = true;
        new Handler().postDelayed(() -> {

            Dialog dialog = new Dialog(GameLayout.this);
            dialog.setContentView(R.layout.win_dialog_layout);
            dialog.setCancelable(false);

            AppCompatImageButton gotoHome, restartGame;
            AppCompatTextView winningPlayer;
            gotoHome = dialog.findViewById(R.id.btn_gotoHome);
            restartGame = dialog.findViewById(R.id.btn_restartGame);
            winningPlayer = dialog.findViewById(R.id.txt_winnerName);

            AppCompatImageView win_logo = dialog.findViewById(R.id.img_winlogo);
            win_logo.setImageResource(R.drawable.win_logo1);

            if(isPlayerOne.equals(true)){
                isPlayerOne = false;
                container_player1.setBackgroundResource(R.drawable.unactive_player);
                container_player2.setBackgroundResource(R.drawable.active_player);
                winningPlayer.setText(playerNameTwo);
                playerTwoWinCounter++;
            }else {
                isPlayerOne = true;
                container_player1.setBackgroundResource(R.drawable.active_player);
                container_player2.setBackgroundResource(R.drawable.unactive_player);
                winningPlayer.setText(playerNameOne);
                playerOneWinCounter++;
            }
            dialog.show();

            final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.game_over);
            mediaPlayer.start();

            restartGame.setOnClickListener(view -> {
                restart();
                LinearLayoutCompat winning_ratio_container = findViewById(R.id.layout_winCounter);
                winning_ratio_container.setVisibility(View.VISIBLE);
                p1_counter.setText(playerOneWinCounter.toString());
                p2_counter.setText(playerTwoWinCounter.toString());
                dialog.dismiss();
            });

            gotoHome.setOnClickListener(view -> {
                Intent gotoHome1 = new Intent(GameLayout.this, MainManue.class);
                startActivity(gotoHome1);
                finish();
            });

            Toast.makeText(getApplicationContext(), "Win", Toast.LENGTH_SHORT).show();
        }, 600);

    }

    @SuppressLint("SetTextI18n")
    public void draw(){
        gameOver = true;
        new Handler().postDelayed(() -> {

            Dialog dialog = new Dialog(GameLayout.this);
            dialog.setContentView(R.layout.win_dialog_layout);
            dialog.setCancelable(false);

            AppCompatImageButton gotoHome, restartGame;
            AppCompatTextView winningPlayer;
            gotoHome = dialog.findViewById(R.id.btn_gotoHome);
            restartGame = dialog.findViewById(R.id.btn_restartGame);
            winningPlayer = dialog.findViewById(R.id.txt_winnerName);
            AppCompatImageView win_logo = dialog.findViewById(R.id.img_winlogo);

            win_logo.setImageResource(R.drawable.draw_game);
            winningPlayer.setText("Draw");
            dialog.show();

            final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.game_over);
            mediaPlayer.start();

            restartGame.setOnClickListener(view -> {
                restart();
                dialog.dismiss();
            });
            gotoHome.setOnClickListener(view -> {
                finishAffinity();
                Intent gotoHome1 = new Intent(GameLayout.this, MainManue.class);
                startActivity(gotoHome1);
                finish();
            });
            Toast.makeText(getApplicationContext(), "Draw Game", Toast.LENGTH_SHORT).show();
        }, 600);


    }

    void drawLine(AppCompatImageButton box1, AppCompatImageButton box2, AppCompatImageButton box3, int background){
        box1.setBackgroundResource(background);
        box2.setBackgroundResource(background);
        box3.setBackgroundResource(background);
    }

    void defaultBackground(AppCompatImageButton box){
        box.setBackgroundResource(DEFAULT_BACKGROUND);
    }

}