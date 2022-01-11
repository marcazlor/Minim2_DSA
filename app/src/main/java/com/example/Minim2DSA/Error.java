package com.example.Minim2DSA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Error extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        String user=getIntent().getStringExtra("user");
        TextView text= findViewById(R.id.errortxt);
        text.setText(user+" not found");
    }

    public void onBackClick(View view) {
        Intent returnIntent = new Intent(Error.this, MainActivity.class);
        startActivity(returnIntent);
    }
}