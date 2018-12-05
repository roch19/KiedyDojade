package com.example.roch.kiedydojade.feature;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddNewPost extends AppCompatActivity
{
    public ArrayList<String> AddNewPostitemList = new ArrayList<>();
    private EditText dateEditText;
    private EditText billEditText;
    public String tekstDoWyslania;
    private EditText todoEditText;
    private ImageButton confirmButton;
    private ImageButton mapButton;
    private ImageButton language;
    private ImageButton back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        loadLocale();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
      //  actionBar.setTitle(getResources().getString(R.string.app_name));

        setContentView(R.layout.addnewpost);
        AddNewPostitemList.add(" ");
        dateEditText = (EditText) findViewById(R.id.Data);
        todoEditText = (EditText) findViewById(R.id.new_localization);
        confirmButton = (ImageButton) findViewById(R.id.confirm);
        mapButton = (ImageButton) findViewById(R.id.maps_button);
        dateEditText.setText(getCurrentDate());
        language = (ImageButton) findViewById(R.id.lang);
        back = (ImageButton) findViewById(R.id.back);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr=" + 53.116611 + "," + 23.146638 + "&daddr=" + 53.121837 + "," + 23.163906;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewPost.this, Options.class);
                startActivity(intent);
                finish();

            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {   AddNewPostitemList = (ArrayList<String>) getIntent().getSerializableExtra("itemList");

             //   AddNewPostitemList.add("Dodano:"+dateEditText.getText().toString()+" Treść:"+todoEditText.getText().toString());
                tekstDoWyslania="Dodano:"+dateEditText.getText().toString()+" Treść:"+todoEditText.getText().toString();


                Intent returnIntent = new Intent();
               // returnIntent.putExtra("AddNewPostitemList", AddNewPostitemList);
                returnIntent.putExtra("AddNewPostitemList", tekstDoWyslania);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("JĘZYK");
                showChangeLanguageDialog();
                loadLocale();
            }

        });


    }






    private void showChangeLanguageDialog() {
        //lista języków
        final String[] listItems = {"Francuski", "Niemiecki", "Angielski"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder( AddNewPost.this);
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
                    System.out.println("ELO");
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


    private String getCurrentDate() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        return dateFormat.format(date);

    }
}
