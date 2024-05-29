package com.umairsaeed.tictactoe1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SpleshScreen extends AppCompatActivity {

    ContentLoadingProgressBar pb;
    Integer counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);

        // for hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // animation
        ImageView logo = findViewById(R.id.img_logo);
        Animation imgAnim = AnimationUtils.loadAnimation(this, R.anim.image_anim);
        logo.startAnimation(imgAnim);

        progress();
    }

    public void progress(){
        pb = findViewById(R.id.progress_bar);

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);

                if(counter == 100){
                    t.cancel();
                    Intent gotoHome = new Intent(SpleshScreen.this, MainManue.class);
                    startActivity(gotoHome);
                    finish();
                }
            }
        };

        t.schedule(tt, 0, 40);
    }
}