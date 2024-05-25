package com.example.maps_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bStart;
    Context context;
    HelperDB helperDB;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, Junction.class);
                startActivity(go);
            }
        });
    }
    public void initComponents(){
        context = this;
        bStart = findViewById(R.id.bStart);
        helperDB = new HelperDB(context);
        db = helperDB.getWritableDatabase();
        db.close();
    }
}