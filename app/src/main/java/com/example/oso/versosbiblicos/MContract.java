package com.example.oso.versosbiblicos;

import android.provider.BaseColumns;

public class MContract{
    public class MColumn implements BaseColumns{
        public static final String TABLE_NAME = "verso";
        public static final String CONTENT = "verseContent";
        public static final String ADDRESS = "verseAddress";

        public static final int COLUMN_CONTENT = 1;
        public static final int COLUMN_ADDRESS = 2;
    }
}
