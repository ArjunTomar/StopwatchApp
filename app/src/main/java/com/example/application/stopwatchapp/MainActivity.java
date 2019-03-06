package com.example.application.stopwatchapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
         RunTimer();
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    } 

    public void onStart(View view)
    {
        running=true;
        Toast v = Toast.makeText(this,"Start Stopwatch",Toast.LENGTH_LONG);
        v.show();
    }

    public void onStop(View view)
    {
       running=false;
        Toast v = Toast.makeText(this,"Stop Stopwatch",Toast.LENGTH_LONG);
        v.show();
    }

    public void onReset(View view)
    {
        running=false;
        seconds=0;
        Toast v = Toast.makeText(this,"Reset Stopwatch",Toast.LENGTH_LONG);
        v.show();
    }

    public void RunTimer()
    {
        final TextView mainText= (TextView) findViewById(R.id.mainText);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int sec=(seconds%60);
                String Time=String.format("%02d/%02d/%02d",hours,minutes,sec);
                mainText.setText(Time);
                if(running)
                   seconds++;
                handler.postDelayed(this,1000);
            }

        });
    }

}
