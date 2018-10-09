package com.example.masho.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainPage extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private Button addpost,showPost;
    private EditText inputField;
    private String uid;
    private Button Signout;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final String TAG = "LoginScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Activities");

        addpost = findViewById(R.id.AddPost);
        showPost = findViewById(R.id.ShowActivity);
        Signout = findViewById(R.id.Signout);



        inputField = findViewById(R.id.InputActivity);

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
            }
        });

        showPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainPage.this,ShowActivity.class);
                startActivity(i);
            }
        });
        addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingData();
            }
        });
        setupFirebaseListener();
    }

    private void setupFirebaseListener() {
        Log.d(TAG, "setupFirebaseListener: setting up the auth state listener.");
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged( FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }

    private void AddingData()
    {
        String input = inputField.getText().toString();
     if(TextUtils.isEmpty(input))
     {
         Toast.makeText(getApplicationContext(), "Insert Activity", Toast.LENGTH_LONG).show();
     }
     else {
         databaseReference = FirebaseDatabase.getInstance().getReference("Activities").child(input);
         databaseReference.child("Activity").setValue(input);
         databaseReference.child("UserId").setValue(uid);
         Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_LONG).show();
     }
    }
}
