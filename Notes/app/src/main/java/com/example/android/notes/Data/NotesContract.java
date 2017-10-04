package com.example.android.notes.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Asus on 29-Aug-17.
 */

public final class NotesContract {

    public static  final String CONTENT_AUTHORITY = "com.example.android.notes";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final  String PATH_NOTES ="notes";

    private  NotesContract(){

    }

public  static  final class NoteEntry implements BaseColumns{




    public static  final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_NOTES);

    public static final String TABLE_NAME= "Notes";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_DESCRIPTION = "Description";
    public static  final String COLUMN_DATE= "Date";
    public static  final String COLUMN_TIME= "Time";




}


}


