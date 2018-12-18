package com.example.roch.kiedydojade.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roch.kiedydojade.feature.DataBase.StacionModels;
import com.example.roch.kiedydojade.feature.DataBase.StationViewModel;

import static com.example.roch.kiedydojade.feature.AddNewPost.EXTRA_REPLY;

public class EditPost extends AppCompatActivity {
    private int position;
    private EditText et;
    private StacionModels stacionModels = null;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpost_layout);

        this.position = getIntent().getIntExtra("position", -1);

        et = findViewById(R.id.editText);

        for( int i = 0; i < MenuActivity.stationListAdapter.getHolders().size(); i++ ){
            if(MenuActivity.stationListAdapter.getHolders().get(i).position == position) {
                stacionModels = MenuActivity.stationListAdapter.getHolders().get(i).stacionModels;
            }
        }

        if(stacionModels != null){
            et.setText(stacionModels.getDestinationName());
        }


        btn = findViewById(R.id.buttonEdit);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editStaction();
//            }
//        });


       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  editStaction();

                edit();
            }
        });

    }



    void edit(){
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(et.getText())){
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String word = et.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);
        }
        this.finish();
    }

    void editStaction(){
        StationViewModel documentViewModel = MenuActivity.stationViewModel;

        stacionModels.setDestinationName(et.getText().toString());

        documentViewModel.update(stacionModels);

        setResult(RESULT_OK);

        this.finish();
    }

}
