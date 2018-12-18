package com.example.roch.kiedydojade.feature;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.roch.kiedydojade.feature.DataBase.StacionModels;
import com.example.roch.kiedydojade.feature.DataBase.StationListAdapter;
import com.example.roch.kiedydojade.feature.DataBase.StationViewModel;
import com.example.roch.kiedydojade.feature.Sensors.SensorList;

import  java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity
{
    public static final int NEW_DOCUMENT_ACTIVITY_REQUEST_CODE = 1;
    //public ArrayList<String> itemList;


    //public ArrayAdapter<String> adapter;

    EditText editText;

    private ListView list;
    private ImageButton delete;
    private ImageButton AddPost;
    private ImageButton back;

    public  static StationViewModel stationViewModel;
    public static StationListAdapter stationListAdapter;
    private List<StacionModels> stacionModels;
    private boolean deleteOn=  false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        list = findViewById(R.id.Lista);
        AddPost =  findViewById(R.id.addPost);
        back =  findViewById(R.id.back);
        delete = findViewById(R.id.delete);

//        itemList = new ArrayList<>();
//        itemList.add("WARSZAWA");


        AddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddNewPost.class);
                startActivityForResult(intent, NEW_DOCUMENT_ACTIVITY_REQUEST_CODE);
            }
        });

     //   adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_multiple_choice, itemList);



         stationViewModel = ViewModelProviders.of(this).get(StationViewModel.class);
         stacionModels = stationViewModel.getAllStations().getValue();

         stationListAdapter = new StationListAdapter(getApplicationContext(), stacionModels);

         list.setAdapter(stationListAdapter);

        stationViewModel.getAllStations().observe(this, new Observer<List<StacionModels>>() {
            @Override
            public void onChanged(@Nullable List<StacionModels> stacionModels) {
                stationListAdapter.setStacionList(stacionModels);
            }
        });



//         View.OnClickListener addListner = new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent intent = new Intent(MenuActivity.this, AddNewPost.class);
//                 intent.putStringArrayListExtra("itemList", stacionModels);
//                 startActivityForResult(intent,1);
//                 onActivityResult(1,1,intent);
//                 adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_multiple_choice, itemList);
//                 list.setAdapter(adapter);
//             }
//         };

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(deleteOn){
                    stationViewModel.deleteDocument(list, position);
                    deleteOn = false;
                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /////////////////////////////////
                Intent intent = new Intent(MenuActivity.this, EditPost.class);
                intent.putExtra("position", position);
                startActivity(intent);

                /// KŁAMSTWO WYKRYTE
                startActivityForResult(intent, NEW_DOCUMENT_ACTIVITY_REQUEST_CODE);
                stationViewModel.deleteDocument(list, position);

                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MenuActivity.this, Options.class);
              startActivity(intent);
              finish();
            }
        });


//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SparseBooleanArray positionchecker = list.getCheckedItemPositions();
//
//                int count = list.getCount();
//                for (int item = count- 1;item>=0; item--)
//                {
//                    if(positionchecker.get(item)){
//                        adapter.remove(itemList.get(item));
//                       // Toast.makeText(MenuActivity.this, "Sukces!",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                positionchecker.clear();
//                adapter.notifyDataSetChanged();
//                list.setAdapter(adapter);
//            }
//        });



       // list.setAdapter(adapter);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//
//        if (requestCode == 1)
//        {
//
//            if(resultCode == Activity.RESULT_OK)
//            {
//                NEW=data.getStringExtra("AddNewPostitemList");
//                itemList.add(NEW);
//                adapter.notifyDataSetChanged();
//            }
//            if(resultCode == Activity.RESULT_CANCELED)
//            {
//                Toast.makeText(MenuActivity.this, "Someting go wrong :c",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Włączenie usuwania tasków, jednokrotnie
            if(this.deleteOn == true){
                this.deleteOn = false;
                Toast.makeText(this, "Right now you cannot delete document.", Toast.LENGTH_SHORT).show();
            }else{
                this.deleteOn = true;
                Toast.makeText(this, "Right now you can delete document.", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_DOCUMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            StacionModels word = new StacionModels(data.getStringExtra(AddNewPost.EXTRA_REPLY));
            stationViewModel.insert(word);
        } else {
            Toast
                    .makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
        }
    }


}

