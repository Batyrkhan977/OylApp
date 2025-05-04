package com.example.oyla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword;
    Button loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupName = findViewById(R.id.signup_name);
        signupPassword = findViewById(R.id.signup_password);
        signupUsername = findViewById(R.id.signup_username);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String login = signupName.getText().toString();
                String password = signupPassword.getText().toString();
                String username = signupUsername.getText().toString();
                String key = reference.push().getKey();
                User user = new User(login, password, username, key);
                Query checkUserDatabase = reference.orderByChild("key").equalTo(key);
                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            signupName.setError("Username already taken");
                        } else if(login.equals("") || password.length() < 8 || username.equals("")){
                            signupName.setError("Incorrect");
                        } else {
                            reference.child(key).setValue(user);
                            Toast.makeText(SignUpActivity.this, "You have signed up successfully!", Toast.LENGTH_LONG).show();
                            Auth.signedInUser = user;
                            SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("key", key);
                            editor.apply();
                            DatabaseReference push = reference.push();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}