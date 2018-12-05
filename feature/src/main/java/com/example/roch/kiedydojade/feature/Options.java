package com.example.roch.kiedydojade.feature;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageButton;

import com.example.roch.kiedydojade.feature.Sensors.Accelerometer;
import com.example.roch.kiedydojade.feature.Sensors.ProximitySensor;
import com.example.roch.kiedydojade.feature.Sensors.SensorList;

import java.util.Locale;

public class Options extends AppCompatActivity {
    private ImageButton exit;
    private ImageButton language;
    private ImageButton bell;
    private ImageButton theme;
    private ImageButton addnew;
    private ImageButton delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.option);
        language = (ImageButton) findViewById(R.id.lang);
        bell = (ImageButton) findViewById(R.id.bell);
        theme = (ImageButton) findViewById(R.id.theme);
        addnew = (ImageButton) findViewById(R.id.addPost);
        exit =(ImageButton) findViewById(R.id.exit);
        delete = (ImageButton) findViewById(R.id.delete);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this,AddNewPost.class );
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, MenuActivity.class );
                startActivity(intent);
                finish();
            }
        });
       /* language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showChangeLanguageDialog();
            }

        });
*/

       language.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Options.this, Accelerometer.class);
               startActivity(intent);
           }
       });

        theme.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Options.this,SensorList.class );
                startActivity(intent);

            }
        });
        bell.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Options.this,ProximitySensor.class );
                startActivity(intent);

            }
        });

    }


    private void showChangeLanguageDialog() {
        //lista języków
        final String[] listItems = {"Francuski", "Niemiecki", "Angielski"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder( Options.this);
        mBuilder.setTitle("Wybierz język..");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0)
                {
                    setLocale("fr");
                    recreate();
                }
                else if (i == 1)
                {
                    setLocale("de");
                    recreate();
                }
                else if (i == 2)
                {
                    setLocale("en");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();

        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    //load language saved in shared preferences
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}
