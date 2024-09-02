package com.example.bootcampbncc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    Button profil;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(HomeActivity.this, "Sedang on create", Toast.LENGTH_SHORT).show();


        image = findViewById(R.id.bncc_image);
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9b9lVVFJtYRrjU1Y_QD1xYnc-nYvzY3lBaA&s").into(image);

        profil = findViewById(R.id.profil_btn);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfil = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(toProfil);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                AppFragment appFragment = new AppFragment();
                transaction.replace(R.id.fragment_layout, appFragment);
                transaction.commit();
            }
        });
    }
}