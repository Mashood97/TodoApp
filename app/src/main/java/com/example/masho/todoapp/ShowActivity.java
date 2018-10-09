package com.example.masho.todoapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ModelShowData> list;
    ModelAdapter adapter;
    ModelShowData modelShowClass;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        list = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();

        uid = user.getUid();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        //scrolling of recycler view
        recyclerView.scrollToPosition(0);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Activities");

        reference.orderByChild("UserId").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    modelShowClass = dataSnapshot1.getValue(ModelShowData.class);
                    list.add(modelShowClass);
                }
                adapter = new ModelAdapter(ShowActivity.this, list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                toast.show();
            }
        });
    }
}
