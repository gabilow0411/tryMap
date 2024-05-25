package com.example.maps_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class View_Location extends AppCompatActivity {
    Context context;
    ListView lvLocations;
    Button bView_Locations;
    ArrayList<String> alLocations = new ArrayList<>();
    ArrayList<geoData> alGeoData = new ArrayList<>();
    ArrayAdapter<String> adapter;
    HelperDB helperDB;
    SQLiteDatabase db;
    String stUri;
    Uri uri;
    Intent Map;
    geoData geoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);

        initComponents();

        bView_Locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=helperDB.getReadableDatabase();
                Cursor cursor=db.query("Locations",null,null,null
                        ,null,null,null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String stName = cursor.getString(0);
                    String stLatitude = cursor.getString(1);
                    String stLongitude = cursor.getString(2);
                    alLocations.add(stName + " Latitude:" + stLatitude + ", Longitude:" + stLongitude);
                    alGeoData.add(new geoData(stName,stLatitude,stLongitude));
                    cursor.moveToNext();
                }
                db.close();
                adapter=new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1,
                        alLocations);
                lvLocations.setAdapter(adapter);
            }
        });
        lvLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                geoData=alGeoData.get(position);
                stUri = "geo:" + geoData.getLatitude() + "," + geoData.getLongitude()+"?z=10";
                uri= Uri.parse(stUri);
                Map=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(Map);
            }
        });
    }
    public void initComponents(){
        context=this;
        lvLocations=findViewById(R.id.lvLocations);
        bView_Locations=findViewById(R.id.bView_Locations);
        helperDB = new HelperDB(context);
    }
}