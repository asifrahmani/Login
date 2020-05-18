package com.example.part3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private Object MainActivity;
    FirebaseAuth mFireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this,Home.class));
            finish();
        }

        final EditText  name=findViewById(R.id.editText);
        final EditText password=findViewById(R.id.editText2);
        TextView info=findViewById(R.id.textView2);
        Button Login=findViewById(R.id.button);
        mFireBaseAuth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= name.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty())
                {
                    name.setError("Please enter email");
                    name.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter correct password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFireBaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign up unsuccessful",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(MainActivity.this,Home.class));
                                finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error occurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }

}