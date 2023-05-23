package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "my_notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOTE_CREATE =
            "CREATE TABLE " + TablesInfo.NoteEntry.TABLE_NAME + " (" +
                    TablesInfo.NoteEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.NoteEntry.COLUMN_NOTE + " TEXT, " +
                    TablesInfo.NoteEntry.COLUMN_CREATE_DATE + " TEXT DEFAULT CURRENT_TIMESTAMP" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NOTE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.NoteEntry.TABLE_NAME);
        onCreate(db);
    }

    public void addNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.NoteEntry.COLUMN_NOTE, note.trim());

        long result = db.insert(TablesInfo.NoteEntry.TABLE_NAME, null, cv);

        if (result > -1)
            Log.i("DatabaseHelper", "Not başarıyla kaydedildi");
        else
            Log.i("DatabaseHelper", "Not kaydedilemedi");

        db.close();
    }

    public void deleteNote(String noteID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TablesInfo.NoteEntry.TABLE_NAME, TablesInfo.NoteEntry.COLUMN_ID + "=?", new String[]{noteID});
        db.close();
    }

    public ArrayList<Notes> getNoteList() {
        ArrayList<Notes> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.NoteEntry.COLUMN_ID,
                TablesInfo.NoteEntry.COLUMN_NOTE};

        Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, projection, null, null, null, null, null);
        while (c.moveToNext()) {
            data.add(new Notes(c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_ID)), c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_NOTE))));
        }

        c.close();
        db.close();

        return data;
    }

}
