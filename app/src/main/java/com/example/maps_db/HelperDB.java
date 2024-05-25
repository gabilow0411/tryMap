package com.example.maps_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
    public class HelperDB extends SQLiteOpenHelper {

        public static final String DB_FILE="all_locations.db";
        public static final String LOCATIONS_TABLE="Locations";
        public static final String LOCATIONS_LATITUDE="Latitude";
        public static final String LOCATIONS_LONGITUDE="Longitude";
        public static final String LOCATIONS_NAME="Name";
        String st;



        public HelperDB(@Nullable Context context)
        {
            super(context, DB_FILE, null, 1 );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            st="CREATE TABLE IF NOT EXISTS ";
            st+=LOCATIONS_TABLE+" ( ";
            st+=LOCATIONS_NAME+" TEXT, ";
            st+=LOCATIONS_LATITUDE+" TEXT, ";
            st+=LOCATIONS_LONGITUDE+" TEXT );";
            db.execSQL(st);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


