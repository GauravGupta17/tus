package com.example.android.notes.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus on 30-Aug-17.
 */

public class NotesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATEBASE_NAME ="Notes.db";

    public  NotesDbHelper(Context context){
        super(context,DATEBASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_NOTES_TABLE =  "CREATE TABLE " + NotesContract.NoteEntry.TABLE_NAME + " ("
                + NotesContract.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NotesContract.NoteEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + NotesContract.NoteEntry.COLUMN_DATE + " TEXT NOT NULL, "
                +NotesContract.NoteEntry.COLUMN_TIME +" TEXT NOT NULL );"
        ;
        sqLiteDatabase.execSQL(SQL_CREATE_NOTES_TABLE);
    }
}
