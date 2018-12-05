package com.example.roch.kiedydojade.feature.Sensors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roch.kiedydojade.feature.MenuActivity;
import com.example.roch.kiedydojade.feature.R;


public class ProximitySensor extends AppCompatActivity
{

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener listener;
    private TextView counter;
    private TextView text;
    private int number_of_close_ups=0;
    int licznikZmian =0;
    char licznik;
    boolean change = false;
    int sensorHelper = 4;
    private TextView number;
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proximity_layout);
        //counter.findViewById(R.id.counter);
       // text.findViewById(R.id.textView);
      //  number=findViewById(R.id.numbers);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(proximitySensor ==null){
            Toast.makeText(this,"You dont hav this feature",Toast.LENGTH_LONG).show();
        }


        listener = new SensorEventListener(){

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if(event.values[0] >= -sensorHelper && event.values[0] <= sensorHelper) {
                        getWindow().getDecorView().setBackgroundColor(Color.RED);

                        if(!change) {
                            change = true;
                            licznikZmian++;
                            }
                        if (licznikZmian== 2) {
                            Toast.makeText(ProximitySensor.this, "Jeszcze jeden a aplikacja ulegnie samozniszczeniu.", Toast.LENGTH_SHORT).show();
                        } else if (licznikZmian > 2) {
                            System.exit(0);
                        }
                    }
                    else{
                        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                            if(change){
                                change= false;
                            }
                        }

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy)
            {

            }
        };
        sensorManager.registerListener(listener,proximitySensor,2*1000*1000);

    }
}
