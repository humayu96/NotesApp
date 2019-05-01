package com.example.notesapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String useruid;
    DatabaseReference databaseReferencel;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;

    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes App");

        firebaseAuth = FirebaseAuth.getInstance();

        useruid = firebaseAuth.getCurrentUser().getUid();
        databaseReferencel = FirebaseDatabase.getInstance().getReference("App").child("Users").child(useruid);

        recyclerView = findViewById(R.id.recyler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton = findViewById(R.id.fab_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mydialoge = new AlertDialog.Builder(NotesActivity.this);


                View view = LayoutInflater.from(NotesActivity.this).inflate(R.layout.custom_input_field,null);

                mydialoge.setView(view);

                final AlertDialog alertDialog = mydialoge.create();

            final EditText  editText_title = view.findViewById(R.id.edit_title);
                final   EditText editText_note = view.findViewById(R.id.edit_note);

                final  Button button = view.findViewById(R.id.button_save);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = editText_title.getText().toString();
                        String note = editText_note.getText().toString();

                        if(TextUtils .isEmpty(title))
                        {
                            Toast.makeText(NotesActivity.this, "TITLE IS REQUIRED", Toast.LENGTH_SHORT).show();
                            return;
                        }

                       else if(TextUtils .isEmpty(note))
                        {
                            Toast.makeText(NotesActivity.this, "NOTE IS REQUIRED", Toast.LENGTH_SHORT).show();
                            return;
                        }

                       String id = databaseReferencel.push().getKey();
                       String datee = DateFormat.getDateInstance().format(new Date());
                       Data data = new Data(title,note,datee,id);

                       databaseReferencel.child(id).setValue(data);
                        Toast.makeText(NotesActivity.this, "Uplode", Toast.LENGTH_SHORT).show();

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
