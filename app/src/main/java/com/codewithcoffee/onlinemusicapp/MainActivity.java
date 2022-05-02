package com.codewithcoffee.onlinemusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    MediaPlayer mediaPlayer;
    ProgressBar progressBar;
    SeekBar seekBar;
    int position;
    String url = "https://dns4.vippendu.com/download/128k-dmmok/Rabb-Karke.mp3";
    Thread updateSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        progressBar = findViewById(R.id.progressBar);
        seekBar = findViewById(R.id.seekBar);

        progressBar.setVisibility(View.VISIBLE);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressBar.setVisibility(View.GONE);
                seekBar.setMax(mediaPlayer.getDuration());

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mediaPlayer.seekTo(seekBar.getProgress());
                    }
                });
                updateSeekBar = new Thread()
                {
                    @Override
                    public void run() {
                        int currentposition = 0;
                        try {
                            while (currentposition<mediaPlayer.getDuration()){
                            }
                            currentposition = mediaPlayer.getCurrentPosition();
                            seekBar.setProgress(currentposition);
                            sleep(700);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                updateSeekBar.start();
            }
        });
        mediaPlayer.prepareAsync();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    img.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                    mediaPlayer.pause();
                }else
                {
                    img.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    mediaPlayer.start();
                }
            }
        });

    }
}