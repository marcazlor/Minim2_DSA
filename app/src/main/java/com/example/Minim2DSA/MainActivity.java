package com.example.Minim2DSA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar spinner;
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        TextView user = findViewById(R.id.userName_Text);
        Button send = findViewById(R.id.send_button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressBar spinner;
                spinner = (ProgressBar)findViewById(R.id.progressBar1);
                //spinner.setVisibility(View.VISIBLE);
                String userName = user.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("User", userName);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), ReposActivity.class);
                startActivity(intent);

                spinner.setVisibility(View.GONE);
            }
        });
    }
}