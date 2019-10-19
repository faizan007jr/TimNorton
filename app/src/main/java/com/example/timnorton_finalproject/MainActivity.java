package com.example.timnorton_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtPassword;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
        }

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        progressDialog = new ProgressDialog(this);

        btnLogin = findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(isValidEmail(email) && !TextUtils.isEmpty(password)) {
                    progressDialog.setMessage("Registering User...");
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Unsuccessful!", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.cancel();
                        }
                    });

                    /*mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Unsuccessful!", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.cancel();
                        }
                    });*/


                } else {
                    if(TextUtils.isEmpty(email)) {
                        txtEmail.setError("Email is invalid");
                    }
                    if(TextUtils.isEmpty(password)) {
                        txtPassword.setError("Password is required");
                    }
                    Toast.makeText(getApplicationContext(), "Email & Password Required!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isValidEmail(String email) {
        if(email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && !TextUtils.isEmpty(email)) {
            return true;
        }
        return false;
    }
}
