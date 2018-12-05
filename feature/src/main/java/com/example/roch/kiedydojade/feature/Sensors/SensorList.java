package com.example.roch.kiedydojade.feature.Sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.roch.kiedydojade.feature.R;

import java.util.List;

public class SensorList extends AppCompatActivity {

    private SensorManager Manager;
    private TextView Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sensor_layout);
        Manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = Manager.getSensorList(Sensor.TYPE_ALL);

        Lista = findViewById(R.id.Title);
        Lista.setText(getResources().getQuantityString(R.plurals.sensors_summary_info,sensorList.size(),sensorList.size()));

        final LinearLayout sensorView = findViewById(R.id.layout);

        for (Sensor sensor : sensorList){
            TextView sensorTextView = new TextView(this);
            sensorTextView.setText(sensor.getType()+" "+sensor.getName());
            sensorView.addView(sensorTextView);
        }

    }
}
