package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNote;
    Button btnSave, btnList, btnDelete ;
    RecyclerView rvNote;
    String noteID = "";
    ArrayList<Notes> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = findViewById(R.id.et_note);
        btnDelete = findViewById(R.id.btn_Delete);

        btnList = findViewById(R.id.btn_List);
        btnSave = findViewById(R.id.btn_Add);
        rvNote = findViewById(R.id.rv_Note);
    }

    public void btn_List_CLick(View view) {
        F_GetList();
    }

    void F_GetList() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        noteList = db.getNoteList();

        NoteAdapter adp = new NoteAdapter(this, noteList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvNote.setLayoutManager(layoutManager);
        rvNote.setHasFixedSize(true);
        rvNote.setAdapter(adp);

        adp.setOnItemClickListener(onItemNoteClickListener);
    }

    View.OnClickListener onItemNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int i = viewHolder.getAdapterPosition();
            Notes item = noteList.get(i);

            etNote.setText(item.getNote_text());
            noteID = item.getNote_id();
        }
    };

    public void btn_Save_Click(View view) {
        if (!etNote.getText().toString().trim().equals("")) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.addNote(etNote.getText().toString());

            db.close();
            etNote.setText("");
        }
    }

    public void btn_Delete_Click(View view) {
        if (!noteID.equals("")) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.deleteNote(noteID);
            db.close();

            Toast.makeText(getApplicationContext(), "Not silindi", Toast.LENGTH_SHORT).show();
            noteID = "";
            etNote.setText("");
            F_GetList();
        } else
            Toast.makeText(getApplicationContext(), "Lütfen silinecek notu seçiniz", Toast.LENGTH_SHORT).show();
    }



}