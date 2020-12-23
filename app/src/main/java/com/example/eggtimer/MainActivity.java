package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekbar;
    TextView timerTextview;
    Boolean counterIsActive = false;
    Button button;
    CountDownTimer countDownTimer;
    public void updateTimer(int i)
    {
        int min = (int)i/60;
        int sec = i-min *60;
        String seconfString = Integer.toString(sec);
        if(sec<=9)
            seconfString="00";
        else if(sec <=9)
        {
            seconfString= "0"+seconfString;
        }

        timerTextview.setText(Integer.toString(min)+":"+ Integer.toString(sec));
    }
    public void resetTimer()
    {
        timerTextview.setText("0.30");
        timerSeekbar.setProgress(30);
        countDownTimer.cancel();
        button.setText("Go!!");
        timerSeekbar.setEnabled(true);
        counterIsActive=false;
    }
    public void counterTimer(View view)

    {
        if(counterIsActive == false) {
            counterIsActive = true;
            timerSeekbar.setEnabled(false);
            button.setText("Stop!!");
            countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000)
        {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer media = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    media.start();
                    ;
                    Log.i("finished", "timer done");
                }
            }.start();
        }
        else
        {
           resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar = findViewById(R.id.seekBar);
        timerTextview = findViewById(R.id.textView2);
        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        button = findViewById(R.id.button);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}