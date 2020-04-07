package com.example.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button incrementer,decrementer;
    Button setRange,toRate;
    TextView maxRatingRange;
    int range;



    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.decrementer : decrement();
                    break;
                case R.id.incrementer : increment();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        incrementer=(Button) findViewById( R.id.incrementer );
        decrementer=(Button) findViewById( R.id.decrementer );
        maxRatingRange=(TextView)findViewById( R.id.maxRatingRange );
        setRange=(Button)findViewById( R.id.setRange );

        incrementer.setOnClickListener( clickListener );
        decrementer.setOnClickListener( clickListener );
        initRange();


        toRate=(Button)findViewById( R.id.toRate );
        toRate.setText( "Rating 1-"+maxRatingRange.getText() );
        setRange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRate.setText( "Rating 1-"+maxRatingRange.getText() );
            }
        } );

        toRate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this , RatingSlider.class );
                i.putExtra( "maxRating" , maxRatingRange.getText().toString() );
                startActivity( i );
            }
        } );
        Button history;
        history=(Button)findViewById( R.id.historyBtn );
        history.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( MainActivity.this , HistoryRating.class ) );
            }
        } );

    }
    private void initRange()
    {
        range = 5;
        maxRatingRange.setText( ""+range );
    }
    private void decrement()
    {
        if(range>5)
        {
            range--;
            maxRatingRange.setText( ""+ range );
        }

    }
    private void increment()
    {
        if(range<9)
        {
            range++;
            maxRatingRange.setText(""+ range );
        }
    }



}
