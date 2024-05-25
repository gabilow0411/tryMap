package com.example.maps_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Location extends AppCompatActivity {
    Context context;
    EditText etName,etLatitude,etLongitude;
    Button  bAdd_Location,bGoMap;
    String stName,stLatitude,stLongitude,stUri;
    Uri uri;
    Intent Map;
    HelperDB helperDB;
    ContentValues cv=new ContentValues();
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        initComponents();

        bGoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            stLatitude = etLatitude.getText().toString();
            stLongitude = etLongitude.getText().toString();
            stUri = "geo:" + stLatitude + "," + stLongitude+"?z=10";
            uri=Uri.parse(stUri);
            Map=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(Map);
            }
        });

        bAdd_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etLatitude.equals("") || etLongitude.equals("") || etName.equals("")) {
                    Toast.makeText(context, "all fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                stName = etName.getText().toString();
                stLatitude = etLatitude.getText().toString();
                stLongitude = etLongitude.getText().toString();

                cv.put(HelperDB.LOCATIONS_NAME, stName);
                cv.put(HelperDB.LOCATIONS_LATITUDE, stLatitude);
                cv.put(HelperDB.LOCATIONS_LONGITUDE, stLongitude);
                db=helperDB.getWritableDatabase();
                db.insert(HelperDB.LOCATIONS_TABLE,null,cv);
                db.close();
                etName.setText("");
                etLatitude.setText("");
                etLongitude.setText("");
                Toast.makeText(context, "Location added", Toast.LENGTH_SHORT).show();
                Intent go=new Intent(context, Add_Location.class);
            }
        });
    }
    public void initComponents(){
        context = this;
        etName = findViewById(R.id.etName);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        bAdd_Location = findViewById(R.id.bAdd_Location);
        bGoMap = findViewById(R.id.bGoMap);
        helperDB = new HelperDB(context);
    }

}