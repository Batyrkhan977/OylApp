package com.example.oyla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        EditText login = findViewById(R.id.name);
        EditText passwd = findViewById(R.id.password);
        Button signupRedirectText = findViewById(R.id.signupRedirectText);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            if(login == null || login.getText().toString().isEmpty()){
                login.setError("Can't have an empty login.");
            }else if(passwd == null || passwd.getText().toString().length( ) < 8){
                passwd.setError("Password must contain at least 8 characters.");
            }else{
                String userLogin = login.getText().toString();
                String userPassword = passwd.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("users");
                Query checkUserDatabase = usersRef.orderByChild("login").equalTo(userLogin);
                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            login.setError(null);
                            String passwordFromDB = snapshot.child(userLogin).child("password").getValue(String.class);
                            if (passwordFromDB.equals(userPassword)) {
                                login.setError(null);
                                String nameFromDB = snapshot.child(userLogin).child("username").getValue(String.class);
                                String key = snapshot.child(userLogin).child("key").getValue(String.class);
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("key", key);
                                editor.apply();
                                Auth.signedInUser = snapshot.child(userLogin).getValue(User.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                login.setError("password invalid");
                            }
                        } else {
                            login.setError("Username or password invalid");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                })
                ;}
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}