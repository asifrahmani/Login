package com.example.part3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Button logout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = findViewById(R.id.logout);
        mFirebaseAuth=FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getInstance().signOut();
                Intent i=new Intent(Home.this,MainActivity.class);
                startActivity(i);


            }
        });

    }
}