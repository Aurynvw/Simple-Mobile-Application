package com.example.bootcampbncc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView email, name;
    SharedPreferences sp;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences("data", MODE_PRIVATE);

        email = findViewById(R.id.email_text);
        name = findViewById(R.id.name_text);

        email.setText(sp.getString("email", ""));
        name.setText(sp.getString("name", ""));

        back = findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backHome = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(backHome);
            }
        });
    }
}