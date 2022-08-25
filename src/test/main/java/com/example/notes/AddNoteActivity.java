package com.example.notes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import io.realm.Realm;



public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // used to interact with the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.titleinput);  //searches a view made by the XML attribute
        EditText detailInput = findViewById(R.id.detailinput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        Realm.init(getApplicationContext());  //initialize realm database
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //for the save button to show on interface after inputting
                String title = titleInput.getText().toString();   //converts inputted text from user into string
                String detail = detailInput.getText().toString();
                long time = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);  //creating object
                note.setTitle(title);
                note.setDetail(detail);
                note.setTime(time);
                realm.commitTransaction(); // saving changes in database
                Toast.makeText(getApplicationContext(),"Reminder Saved!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}