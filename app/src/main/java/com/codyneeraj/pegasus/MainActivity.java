package com.codyneeraj.pegasus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.Neeraj);
        Log.d("Neeraj", "Error");
    }

    public void clicked(View view) {
        txt.setText(R.string.onClick);
        Toast.makeText(this, "Textview CLicked !", Toast.LENGTH_SHORT).show();
    }

}