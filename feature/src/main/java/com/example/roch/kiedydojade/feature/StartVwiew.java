package com.example.roch.kiedydojade.feature;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import  java.io.Serializable;

import java.util.ArrayList;

public class StartVwiew extends AppCompatActivity{

        private ImageButton option;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_view);
        option = (ImageButton) findViewById(R.id.options);

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartVwiew.this, Options.class);
                startActivity(intent);
                finish();
            }
        });


        }
    }


