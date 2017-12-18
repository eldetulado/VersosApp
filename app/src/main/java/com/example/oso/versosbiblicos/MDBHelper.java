package com.example.oso.versosbiblicos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MDBHelper extends SQLiteOpenHelper {
    private static final String NAME_DATABASE = "versos.db";
    private static final int VERSION_DATABASE = 1;

    public MDBHelper(Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String VERSOS_DATABASE = "CREATE TABLE " + MContract.MColumn.TABLE_NAME + "( " +
                MContract.MColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MContract.MColumn.CONTENT + " TEXT NOT NULL, " +
                MContract.MColumn.ADDRESS + " TEXT NOT NULL " + ")";

        sqLiteDatabase.execSQL(VERSOS_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MContract.MColumn.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
