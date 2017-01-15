package com.moonconsulting.traptracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RoundActivity extends AppCompatActivity {

    int countHit=0;
    int station=1;
    TextView stationView;
    TextView hitView;
    Map<Integer,Integer> round= new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        stationView=(TextView)findViewById(R.id.textView_station);
        stationView.setText(String.valueOf(station));
        hitView=(TextView)findViewById(R.id.textView_hit);
        hitView.setText(String.valueOf(countHit));
    }

    public void addHit(View view){
        if (countHit == 5){
            changeStation(view);
        }
        countHit++;
        hitView.setText(String.valueOf(countHit));
    }
    public void changeStation(View view){
        if (round.containsKey(station)) {
            round.replace(station, countHit);
        }else{
            round.put(station,countHit);
        }
        if (station < 5){
            station++;
        }else{
            station=1;
        }
        countHit=round.getOrDefault(station,0);
        stationView.setText(String.valueOf(station));
        hitView.setText(String.valueOf(countHit));
    }
    public void finishRound(View view){
        int totalHit=0;
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for (Map.Entry<Integer, Integer> entry : round.entrySet()) {
            totalHit += entry.getValue();
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        };
        sb.append("]");

        FileOutputStream outputStream;
        String data= new SimpleDateFormat("MM/dd/YYYY HH:mm").format(new Date()) + "|" + totalHit + "/25|"  + sb.toString() + "\n";
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
