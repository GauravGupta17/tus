package com.example.android.notes.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Asus on 03-Sep-17.
 */

public class NotesProvider extends ContentProvider {

    NotesDbHelper notesDbHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
private static final int NOTES =100;
    private static final int NOTES_ID = 101;



    static {
        sUriMatcher.addURI(NotesContract.CONTENT_AUTHORITY, NotesContract.PATH_NOTES,NOTES);
        sUriMatcher.addURI(NotesContract.CONTENT_AUTHORITY,NotesContract.PATH_NOTES+ "/#",NOTES_ID);


    }

    @Override
    public boolean onCreate() {
      notesDbHelper = new NotesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = notesDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTES:
                cursor = database.query(NotesContract.NoteEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                 break;
            case NOTES_ID:
                selection = NotesContract.NoteEntry._ID +"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(NotesContract.NoteEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                 break;
             default:
                 throw new IllegalArgumentException("Cannot parse in query method");


        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case NOTES:
                return insertNote(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not successful in the insert method");

        }
    }

    public Uri insertNote(Uri uri, ContentValues contentValues){

        SQLiteDatabase database =  notesDbHelper.getWritableDatabase();

        String Description = contentValues.getAsString(NotesContract.NoteEntry.COLUMN_DESCRIPTION);

        if (Description==null){
            throw new IllegalArgumentException("A NOTE is REquired");

        }
long id =database.insert(NotesContract.NoteEntry.TABLE_NAME,null,contentValues);

if (id==-1){
    Log.e("INSERT DATA","Failed to Insert");

}
        if(id== -1){
            Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
        }

getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = notesDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDelete=0;
         switch (match){
             case NOTES:

                 rowsDelete= database.delete(NotesContract.NoteEntry.TABLE_NAME,selection,selectionArgs);

             case NOTES_ID:
                 selection = NotesContract.NoteEntry._ID +"=?";
                 selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                 rowsDelete= database.delete(NotesContract.NoteEntry.TABLE_NAME,selection,selectionArgs);

             default:

         }



        if(rowsDelete!=0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }



        return rowsDelete;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        int match = sUriMatcher.match(uri);
        switch (match)
        {
            case NOTES:
                return updateNote(uri,contentValues,selection,selectionArgs);
            case NOTES_ID:
                selection = NotesContract.NoteEntry._ID +"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                return updateNote(uri,contentValues,selection,selectionArgs);

        default:
            throw new IllegalArgumentException("Update Failed");


        }
    }
    public int updateNote(Uri uri,ContentValues contentValues,String selection,String [] selectionArgs){

        int rowsupdate =0;
        if(contentValues.containsKey(NotesContract.NoteEntry.COLUMN_DESCRIPTION)) {


            String description = contentValues.getAsString(NotesContract.NoteEntry.COLUMN_DESCRIPTION);

            if (description== null){
                throw new IllegalArgumentException("Notes Empty");
            }

        }
        if (contentValues.size()==0){
            return 0;
        }



    SQLiteDatabase database = notesDbHelper.getWritableDatabase();

      rowsupdate = database.update(NotesContract.NoteEntry.TABLE_NAME,contentValues,selection,selectionArgs);

     if (rowsupdate!=0){
         getContext().getContentResolver().notifyChange(uri,null);
     }

    return rowsupdate;
    }







}
