package com.example.myratingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RatingStorage.db";
    public static final String TABLE_NAME = "rating_table";


    public SqlDatabase(@Nullable Context context) {
        super( context, DATABASE_NAME , null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + TABLE_NAME + "(RATE VARCHAR(255), DATE VARCHAR(255) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL( "DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate( db );
    }
    public boolean insertData(String rate,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( "RATE" , rate );
        contentValues.put( "DATE" , date );
        long result = db.insert(TABLE_NAME , null , contentValues );
        if(result == -1)
            return false;
        else
            return true;
    }
    public ArrayList getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String>arrayList=new ArrayList<String> ();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME , null );
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            arrayList.add( cursor.getString( cursor.getColumnIndex( "RATE" ) ) +"  \t  \t  \t"+ cursor.getString( cursor.getColumnIndex( "DATE" ) )  );
            cursor.moveToNext();
        }
        return arrayList;
    }


}
