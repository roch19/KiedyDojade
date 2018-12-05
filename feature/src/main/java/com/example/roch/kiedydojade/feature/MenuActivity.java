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
import java.util.List;


public class MenuActivity extends AppCompatActivity
{
    public ArrayList<String> itemList;
    //public ArrayList<String> NEW;
    public String NEW=" ";
    public ArrayAdapter<String> adapter;
    EditText editText;
    ListView list;
    private ImageButton delete;
    private ImageButton AddPost;
    private ImageButton back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        list = findViewById(R.id.Lista);
        AddPost =  findViewById(R.id.addPost);
        back =  findViewById(R.id.back);
        delete = findViewById(R.id.delete);

        itemList = new ArrayList<>();
        itemList.add("WARSZAWA");

        adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_multiple_choice, itemList);

        // itemList.add("Text");

         list.setAdapter(adapter);

//         AddPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MenuActivity.this, AddNewPost.class);
//                intent.putStringArrayListExtra("itemList", itemList);
//                startActivityForResult(intent,1);
//                onActivityResult(1,1,intent);
//            }
//        });

         View.OnClickListener addListner = new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MenuActivity.this, AddNewPost.class);
                 intent.putStringArrayListExtra("itemList", itemList);
                 startActivityForResult(intent,1);
                 onActivityResult(1,1,intent);
                 adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_multiple_choice, itemList);
                 list.setAdapter(adapter);
             }
         };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MenuActivity.this, Options.class);
              startActivity(intent);
              finish();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionchecker = list.getCheckedItemPositions();
                int count = list.getCount();

                for (int item = count- 1;item>=0; item--)
                {
                    if(positionchecker.get(item)){
                        adapter.remove(itemList.get(item));
                        Toast.makeText(MenuActivity.this, "Sukces!",Toast.LENGTH_SHORT).show();
                    }
                }
                positionchecker.clear();
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
            }
        });

        AddPost.setOnClickListener(addListner);
        list.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 1)
        {

            if(resultCode == Activity.RESULT_OK)
            {
               // itemList.add(data.getStringArrayListExtra("AddNewPostitemList"));
                NEW=data.getStringExtra("AddNewPostitemList");
                itemList.add(NEW);
                //Toast.makeText(MenuActivity.this, itemList.toString(),Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
            if(resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(MenuActivity.this, "Someting go wrong :c",Toast.LENGTH_SHORT).show();
            }
        }
    }


}

