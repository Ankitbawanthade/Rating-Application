package com.example.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryRating extends AppCompatActivity {
     private SqlDatabase myDb;
     ArrayList arrayList;
     ArrayAdapter arrayAdapter;
     ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_history_rating );


        myDb = new SqlDatabase( HistoryRating.this );
        arrayList = myDb.getAllData();
        arrayAdapter = new ArrayAdapter( HistoryRating.this , android.R.layout.simple_list_item_1,arrayList );
        listView = (ListView)findViewById( R.id.list_view );
        listView.setAdapter( arrayAdapter );

    }

}
