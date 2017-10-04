package com.example.android.notes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.notes.Data.NotesContract;

/**
 * Created by Asus on 15-Sep-17.
 */

public class NoteCursorAdapter extends CursorAdapter {
    Context context;
    public NoteCursorAdapter(Context context,Cursor cursor){
        super(context,cursor,0);
        this.context =context;
    }





    @Override
    public View newView(Context context, Cursor cursor, ViewGroup Parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_view,Parent,false);


    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView description =(TextView) view.findViewById(R.id.Desciption);
        final String desc = cursor.getString(cursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_DESCRIPTION));

        description.setText(desc);

    }






}



