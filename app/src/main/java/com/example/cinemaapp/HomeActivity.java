package com.example.cinemaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        //Toast.makeText(getApplicationContext(),"Welcome " + username,Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        CardView action = findViewById(R.id.cardAction);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, MoviesList.class);
                it.putExtra("title","Action Movies");
                startActivity(it);
            }
        });

        CardView comedy = findViewById(R.id.cardComedy);
        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, MoviesList.class);
                it.putExtra("title","Comedy Movies");
                startActivity(it);
            }
        });

        CardView romance = findViewById(R.id.cardRomance);
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, MoviesList.class);
                it.putExtra("title","Romance Movies");
                startActivity(it);
            }
        });

        CardView drama = findViewById(R.id.cardDrama);
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, MoviesList.class);
                it.putExtra("title","Drama Movies");
                startActivity(it);
            }
        });

        CardView tickets = findViewById(R.id.cardTickets);
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, TicketsList.class);
                startActivity(it);
            }
        });


    }
}