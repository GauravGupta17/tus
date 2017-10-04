package com.example.android.notes;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.android.notes.Data.NotesContract;

public class notes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private  EditText notes;

  private String note = null;
private String Date= "";
    NoteCursorAdapter mAdapter;
    private String Time = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


         notes = (EditText) findViewById(R.id.edit);








    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_save:
                Insert();
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    public void Insert (){



        note = notes.getText().toString();

        ContentValues values = new ContentValues();

        values.put(NotesContract.NoteEntry.COLUMN_DESCRIPTION,note);
        values.put(NotesContract.NoteEntry.COLUMN_DATE,Date);
        values.put(NotesContract.NoteEntry.COLUMN_TIME,Time);

        Uri uri = getContentResolver().insert(NotesContract.NoteEntry.CONTENT_URI,values);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
mAdapter.swapCursor(null);
    }
}
