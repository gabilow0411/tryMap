package com.example.maps_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Junction extends AppCompatActivity {
    Button bView, bAdd;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junction);

        initComponents();

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, Add_Location.class);
                startActivity(go);
            }
        });
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, View_Location.class);
                startActivity(go);
            }
        });
    }
    public void initComponents(){
        context = this;
        bView = findViewById(R.id.bView);
        bAdd = findViewById(R.id.bAdd);
    }
}