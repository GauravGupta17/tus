package com.example.android.notes;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.android.notes.Data.NotesContract;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Uri CurrentUri;
    NoteCursorAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView)findViewById(R.id.List_item);
        mCursorAdapter = new NoteCursorAdapter(this,null);
        listView.setAdapter(mCursorAdapter);

        FloatingActionButton  button = (FloatingActionButton) findViewById(R.id.floatingActionButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this,notes.class);
                        startActivity(i);
                    }
                });

getLoaderManager().initLoader(0,null,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }




    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        CursorLoader cursorLoader ;
        String [] projection ={
                NotesContract.NoteEntry._ID, NotesContract.NoteEntry.COLUMN_DESCRIPTION, NotesContract.NoteEntry.COLUMN_TIME, NotesContract.NoteEntry.COLUMN_DATE};

        return new CursorLoader(this,NotesContract.NoteEntry.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
mCursorAdapter.swapCursor(null);
    }
}