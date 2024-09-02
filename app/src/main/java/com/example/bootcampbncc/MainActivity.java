package com.example.bootcampbncc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, name, password, confirmPassword;
    Button register;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        sp = getSharedPreferences("data", MODE_PRIVATE);
        edit = sp.edit();

        email = findViewById(R.id.email_edit);
        name = findViewById(R.id.name_edit);
        password = findViewById(R.id.password_edit);
        confirmPassword = findViewById(R.id.confirmPassword_edit);
        register = findViewById(R.id.registration_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = email.getText().toString();
                String nameStr = name.getText().toString();
                String passwordStr = password.getText().toString();
                String confirmPasswordStr = confirmPassword.getText().toString();

                if (nameStr.length() < 5) {
                    Toast.makeText(MainActivity.this, "Nama harus setidaknya 5 karakter", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!emailStr.contains("@") || !emailStr.endsWith(".com")) {
                    Toast.makeText(MainActivity.this, "Email harus mengandung @ dan diakhiri dengan .com", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!passwordStr.equals(confirmPasswordStr)) {
                    Toast.makeText(MainActivity.this, "Kata sandi dan konfirmasi harus sama", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    edit.putString("email", emailStr);
                                    edit.putString("name", nameStr);
                                    edit.apply();
                                    Intent toHome = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(toHome);
                                } else {
                                    Toast.makeText(MainActivity.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
