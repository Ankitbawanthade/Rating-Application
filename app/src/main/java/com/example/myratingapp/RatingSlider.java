package com.example.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RatingSlider extends AppCompatActivity {
    SeekBar seekbar;
    TextView rating;
    Button submit;
     private SqlDatabase myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rating_slider );

        Intent i = getIntent();
        String n = i.getStringExtra( "maxRating" );
        seekbar=(SeekBar)findViewById( R.id.seekBar );
        rating =(TextView)findViewById( R.id.rating );
        submit=(Button)findViewById( R.id.submitBtn );
        myDb= new SqlDatabase( this );
        seekbar.setMax( Integer.parseInt( n )  );
        seekbar.setProgress( 1 );
        seekbar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rating.setText( "" + i );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        AddData();

    }
    public void AddData()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM hh:mm:ss a" );
        final String registrationdatetime = simpleDateFormat.format(calendar.getTime());
        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 boolean isInserted = myDb.insertData( rating.getText().toString() ,registrationdatetime  );
                 if(isInserted == true)
                     Toast.makeText( RatingSlider.this , "Your Rating submitted",Toast.LENGTH_LONG ).show();
                 else
                     Toast.makeText( RatingSlider.this , "Your Rating was not submitted",Toast.LENGTH_LONG ).show();

            }
        } );
    }
}
