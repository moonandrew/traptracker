package com.moonconsulting.traptracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoundActivity extends AppCompatActivity {

    int countHit=0;
    int countMiss=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

    }

    public void addHit(View view){
        countHit++;
    }
    public void addMiss(View view){
        countMiss++;
    }
    public void finishRound(View view){
        FileOutputStream outputStream;
        String data= new SimpleDateFormat("MM/dd/YYYY HH:mm").format(new Date()) + "|" + countHit + "|" + countMiss + "\n";
        try {
            outputStream = openFileOutput(getResources().getString(R.string.resultsFile), Context.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        finish();
    }

}
