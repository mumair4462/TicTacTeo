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

import java.util.ArrayList;

public class GameOnePlayerLayout extends AppCompatActivity {

    private static final int HORIZENTAL_LINE = R.drawable.line_horizental;
    private static final int VERTICAL_LINE = R.drawable.line_vertical;
    private static final int CROSS_LINE = R.drawable.line_cross;
    private static final int REVERSE_CROSS_LINE = R.drawable.line_reverse_cross;
    private static final int DEFAULT_BACKGROUND = R.drawable.unactive_player;
    String playerName, gameMode, playerIcon, computerIcon;

    AppCompatTextView humanNameBox, aiNameBox, human_counter, ai_counter;

    LinearLayoutCompat container_player1, container_player2;
    AppCompatImageButton humanIcon, aiIcon;

    int humanCurrentIcon, aiCurrentIcon, totalMoves = 0;
    Integer humanWinCounter = 0, aiWinCounter = 0;

    AppCompatImageButton box1, box2, box3, box4, box5, box6, box7, box8, box9;

    String tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9;

    Boolean isHumanTern = true, gameOver = false;

    ArrayList<AppCompatImageButton> emptyBox = new ArrayList<>();
    ArrayList<AppCompatImageButton> twoSameBox = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one_player_layout);

        //hide title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get value form activity
        Intent getValues = getIntent();
        playerName = getValues.getStringExtra("name");
        playerIcon = getValues.getStringExtra("icon");
        gameMode = getValues.getStringExtra("mode");

        // get all ids
        init();

        humanNameBox.setText(playerName);
        aiNameBox.setText("AI");

        if ("x".equals(playerIcon)) {
            humanCurrentIcon = R.drawable.x;
            aiCurrentIcon = R.drawable.o;
            computerIcon = "o";
        } else {
            humanCurrentIcon = R.drawable.o;
            aiCurrentIcon = R.drawable.x;
            computerIcon = "x";
        }
        humanIcon.setImageResource(humanCurrentIcon);
        aiIcon.setImageResource(aiCurrentIcon);
    }

    public void init(){
        humanNameBox = findViewById(R.id.txt_p1);
        aiNameBox = findViewById(R.id.txt_p2);

        humanIcon = findViewById(R.id.icon_p1);
        aiIcon = findViewById(R.id.icon_p2);

        container_player1 = findViewById(R.id.container_player1);
        container_player2 = findViewById(R.id.container_player2);

        human_counter = findViewById(R.id.txt_p1_count);
        ai_counter =  findViewById(R.id.txt_p2_count);

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

    public void onClicked(View view) {
        AppCompatImageButton btnCurrent = (AppCompatImageButton) view;

        if(!gameOver){

            if(btnCurrent.getTag().toString().equals("c0")){

                final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click_sound);
                mediaPlayer.start();

                if(isHumanTern){
                    btnCurrent.setImageResource(humanCurrentIcon);
                    btnCurrent.setTag(playerIcon);
                    container_player2.setBackgroundResource(R.drawable.active_player);
                    container_player1.setBackgroundResource(R.drawable.unactive_player);
                    isHumanTern = !isHumanTern;
                    totalMoves++;
                    if(totalMoves <= 8){
                        moveAI();
                    }

                }

                updateBtnTags();
                if(tag1.equals(tag2) && tag1.equals(tag3) && !tag1.equals("c0")){
                    drawLine(box1,box2,box3,HORIZENTAL_LINE);
                    win(tag1);
                }else if (tag4.equals(tag5) && tag4.equals(tag6) && !tag4.equals("c0")){
                    drawLine(box4,box5,box6,HORIZENTAL_LINE);
                    win(tag4);
                }else if (tag7.equals(tag8) && tag7.equals(tag9) && !tag7.equals("c0")){
                    drawLine(box7,box8,box9,HORIZENTAL_LINE);
                    win(tag7);
                } else if (tag1.equals(tag5) && tag1.equals(tag9) && !tag1.equals("c0")) {
                    drawLine(box1,box5,box9,CROSS_LINE);
                    win(tag1);
                }else if (tag7.equals(tag5) && tag7.equals(tag3) && !tag7.equals("c0")) {
                    drawLine(box7,box5,box3,REVERSE_CROSS_LINE);
                    win(tag7);
                }else if (tag1.equals(tag4) && tag1.equals(tag7) && !tag1.equals("c0")) {
                    drawLine(box1,box4,box7,VERTICAL_LINE);
                    win(tag1);
                }else if (tag2.equals(tag5) && tag2.equals(tag8) && !tag2.equals("c0")) {
                    drawLine(box2,box5,box8,VERTICAL_LINE);
                    win(tag2);
                }else if (tag3.equals(tag6) && tag3.equals(tag9) && !tag3.equals("c0")) {
                    drawLine(box3,box6,box9,VERTICAL_LINE);
                    win(tag3);
                }else if (totalMoves == 9){
                    draw();
                }

            }

        }


    }

    public void moveAI(){
        updateBtnTags();
        fillAllEmptyBox();
        fillTwoSameBox();

        if(!gameOver){
            if(gameMode.equals("easy")){
                if(twoSameBox.size() < 2){
                    int random = (int)(Math.random() * emptyBox.size());
                    AppCompatImageButton aiBox = emptyBox.get(random);
                    aiBox.setImageResource(aiCurrentIcon);
                    aiBox.setTag(computerIcon);
                }else{
                    int random = (int)(Math.random() * twoSameBox.size());
                    AppCompatImageButton aiBox = twoSameBox.get(random);
                    aiBox.setImageResource(aiCurrentIcon);
                    aiBox.setTag(computerIcon);
                }

            }else{
                if(twoSameBox.size() == 0){
                    int random = (int)(Math.random() * emptyBox.size());
                    AppCompatImageButton aiBox = emptyBox.get(random);
                    aiBox.setImageResource(aiCurrentIcon);
                    aiBox.setTag(computerIcon);
                }else{
                    if(twoSameBox.size() == 1){
                        AppCompatImageButton aiBox = twoSameBox.get(0);
                        aiBox.setImageResource(aiCurrentIcon);
                        aiBox.setTag(computerIcon);
                    }else{
                        int random = (int)(Math.random() * twoSameBox.size());
                        AppCompatImageButton aiBox = twoSameBox.get(random);
                        aiBox.setImageResource(aiCurrentIcon);
                        aiBox.setTag(computerIcon);
                    }
                }

            }
            totalMoves++;
            isHumanTern = !isHumanTern;
            container_player2.setBackgroundResource(R.drawable.unactive_player);
            container_player1.setBackgroundResource(R.drawable.active_player);
        }


    }

    public void fillTwoSameBox(){
        twoSameBox.clear();
        String playerIcon = "c0";
        // for 1 row
        if(tag1.equals(tag2) && !tag1.equals("c0") && tag3.equals(playerIcon)){
            twoSameBox.add(box3);
        }
        if(tag2.equals(tag3) && !tag2.equals("c0") && tag1.equals(playerIcon)){
            twoSameBox.add(box1);
        }
        if(tag3.equals(tag1) && !tag3.equals("c0") && tag2.equals(playerIcon)){
            twoSameBox.add(box2);
        }

        // for 2 row
        if(tag4.equals(tag5) && !tag4.equals("c0") && tag6.equals(playerIcon)){
            twoSameBox.add(box6);
        }
        if(tag4.equals(tag6) && !tag4.equals("c0") && tag5.equals(playerIcon)){
            twoSameBox.add(box5);
        }
        if(tag5.equals(tag6) && !tag5.equals("c0") && tag4.equals(playerIcon)){
            twoSameBox.add(box4);
        }

        // for 3 row
        if(tag7.equals(tag8) && !tag7.equals("c0") && tag9.equals(playerIcon)){
            twoSameBox.add(box9);
        }
        if(tag7.equals(tag9) && !tag7.equals("c0") && tag8.equals(playerIcon)){
            twoSameBox.add(box8);
        }
        if(tag8.equals(tag9) && !tag8.equals("c0") && tag7.equals(playerIcon)){
            twoSameBox.add(box7);
        }

        // for 1 column
        if(tag1.equals(tag4) && !tag1.equals("c0") && tag7.equals(playerIcon)){
            twoSameBox.add(box7);
        }
        if(tag1.equals(tag7) && !tag1.equals("c0") && tag4.equals(playerIcon)){
            twoSameBox.add(box4);
        }
        if(tag4.equals(tag7) && !tag4.equals("c0") && tag1.equals(playerIcon)){
            twoSameBox.add(box1);
        }

        // for 2 column
        if(tag2.equals(tag5) && !tag2.equals("c0") && tag8.equals(playerIcon)){
            twoSameBox.add(box8);
        }
        if(tag2.equals(tag8) && !tag2.equals("c0") && tag5.equals(playerIcon)){
            twoSameBox.add(box5);
        }
        if(tag5.equals(tag8) && !tag5.equals("c0") && tag2.equals(playerIcon)){
            twoSameBox.add(box2);
        }

        // for 3 column
        if(tag3.equals(tag6) && !tag3.equals("c0") && tag9.equals(playerIcon)){
            twoSameBox.add(box9);
        }
        if(tag3.equals(tag9) && !tag3.equals("c0") && tag6.equals(playerIcon)){
            twoSameBox.add(box6);
        }
        if(tag6.equals(tag9) && !tag6.equals("c0") && tag3.equals(playerIcon)){
            twoSameBox.add(box3);
        }

        // for diagonal 1
        if(tag1.equals(tag5) && !tag1.equals("c0") && tag9.equals(playerIcon)){
            twoSameBox.add(box9);
        }
        if(tag1.equals(tag9) && !tag1.equals("c0") && tag5.equals(playerIcon)){
            twoSameBox.add(box5);
        }
        if(tag5.equals(tag9) && !tag5.equals("c0") && tag1.equals(playerIcon)){
            twoSameBox.add(box1);
        }

        // for diagonal 2
        if(tag3.equals(tag5) && !tag3.equals("c0") && tag7.equals(playerIcon)){
            twoSameBox.add(box7);
        }
        if(tag3.equals(tag7) && !tag3.equals("c0") && tag5.equals(playerIcon)){
            twoSameBox.add(box5);
        }
        if(tag5.equals(tag7) && !tag5.equals("c0") && tag3.equals(playerIcon)){
            twoSameBox.add(box3);
        }
    }

    public void fillAllEmptyBox(){
        emptyBox.clear();
        if(tag1.equals("c0")){
            emptyBox.add(box1);
        }
        if(tag2.equals("c0")){
            emptyBox.add(box2);
        }
        if(tag3.equals("c0")){
            emptyBox.add(box3);
        }
        if(tag4.equals("c0")){
            emptyBox.add(box4);
        }
        if(tag5.equals("c0")){
            emptyBox.add(box5);
        }
        if(tag6.equals("c0")){
            emptyBox.add(box6);
        }
        if(tag7.equals("c0")){
            emptyBox.add(box7);
        }
        if(tag8.equals("c0")){
            emptyBox.add(box8);
        }
        if(tag9.equals("c0")){
            emptyBox.add(box9);
        }
    }

    public void updateBtnTags(){
        tag1 = box1.getTag().toString();
        tag2 = box2.getTag().toString();
        tag3 = box3.getTag().toString();
        tag4 = box4.getTag().toString();
        tag5 = box5.getTag().toString();
        tag6 = box6.getTag().toString();
        tag7 = box7.getTag().toString();
        tag8 = box8.getTag().toString();
        tag9 = box9.getTag().toString();
    }


    @SuppressLint("SetTextI18n")
    public void win(String st){
        gameOver = true;
        new Handler().postDelayed(() -> {

            Dialog dialog = new Dialog(GameOnePlayerLayout.this);
            dialog.setContentView(R.layout.win_dialog_layout);
            dialog.setCancelable(false);

            AppCompatImageButton gotoHome, restartGame;
            AppCompatTextView winningPlayer;
            gotoHome = dialog.findViewById(R.id.btn_gotoHome);
            restartGame = dialog.findViewById(R.id.btn_restartGame);
            winningPlayer = dialog.findViewById(R.id.txt_winnerName);

            AppCompatImageView dialog_logo = dialog.findViewById(R.id.img_winlogo);

            if(st.equals(playerIcon)){
                Toast.makeText(GameOnePlayerLayout.this, "You Win", Toast.LENGTH_SHORT).show();
                dialog_logo.setImageResource(R.drawable.win_logo1);
                winningPlayer.setText("You Win");
                humanWinCounter++;
            }else {
                winningPlayer.setText("You Loss");
                dialog_logo.setImageResource(R.drawable.loss_logo);
                Toast.makeText(GameOnePlayerLayout.this, "You Loss", Toast.LENGTH_SHORT).show();
                aiWinCounter++;
            }
            dialog.show();
            restartGame.setOnClickListener(view -> {
                restart();
                LinearLayoutCompat winning_ratio_container = findViewById(R.id.layout_winCounter);
                winning_ratio_container.setVisibility(View.VISIBLE);
                human_counter.setText(humanWinCounter.toString());
                ai_counter.setText(aiWinCounter.toString());
                dialog.dismiss();
            });

            gotoHome.setOnClickListener(view -> {
                finishAffinity();
                Intent gotoHome1 = new Intent(GameOnePlayerLayout.this, MainManue.class);
                startActivity(gotoHome1);
                finish();
            });

            final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_over);
            mediaPlayer.start();
        }, 600);
    }

    public void restart(){
        isHumanTern = true;
        gameOver = false;
        container_player1.setBackgroundResource(R.drawable.active_player);
        container_player2.setBackgroundResource(R.drawable.unactive_player);
        totalMoves = 0;

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
    public void draw(){
        gameOver = true;


        new Handler().postDelayed(() -> {

            Dialog dialog = new Dialog(GameOnePlayerLayout.this);
            dialog.setContentView(R.layout.win_dialog_layout);
            dialog.setCancelable(false);

            AppCompatImageButton gotoHome, restartGame;
            AppCompatTextView winningPlayer;
            gotoHome = dialog.findViewById(R.id.btn_gotoHome);
            restartGame = dialog.findViewById(R.id.btn_restartGame);
            winningPlayer = dialog.findViewById(R.id.txt_winnerName);
            AppCompatImageView dialog_logo = dialog.findViewById(R.id.img_winlogo);
            dialog_logo.setImageResource(R.drawable.draw_game);
            winningPlayer.setText("Draw");
            dialog.show();
            restartGame.setOnClickListener(view -> {
                restart();
                dialog.dismiss();
            });

            gotoHome.setOnClickListener(view -> {
                Intent gotoHome1 = new Intent(GameOnePlayerLayout.this, MainManue.class);
                startActivity(gotoHome1);
                finish();
            });

            final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_over);
            mediaPlayer.start();
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