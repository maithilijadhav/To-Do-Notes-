package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton addNoteBtn = findViewById(R.id.addnewnotebtn);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {  //when add new reminder is created
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
            }
        });

        Realm.init(getApplicationContext()); // initialize realm
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).findAll();  // gets data from realm and displays the notes

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter Adapter = new Adapter(getApplicationContext(),notesList); // recieve data from notesList
        recyclerView.setAdapter(Adapter);  // will get data from Adapter

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {  // if the note gets changed
            @Override
            public void onChange(RealmResults<Note> notes) {
                Adapter.notifyDataSetChanged();
            }
        });
    }
}