package com.example.cepl.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CEPL on 09-02-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "svp2.db";
    public static final String TABLE_NAME = "svp2_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MOBILE_NO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS sections");
        db.execSQL("DROP TABLE IF EXISTS sub_section");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS answers");
        db.execSQL("DROP TABLE IF EXISTS options");

        db.execSQL("create table sections (ID INTEGER PRIMARY KEY AUTOINCREMENT, SECTION_NAME TEXT)");
        db.execSQL("create table sub_section (ID INTEGER PRIMARY KEY AUTOINCREMENT, SECTION_ID INTEGER, SUB_SECTION_NAME TEXT, FOREIGN KEY(SECTION_ID) REFERENCES sections(ID))");
        db.execSQL("create table questions (ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION_DETAIL TEXT, DIFFICULTY_LEVEL TEXT, SECTION_ID INTEGER, SUB_SECTION_ID INTEGER, FOREIGN KEY(SECTION_ID) REFERENCES section(ID), FOREIGN KEY(SUB_SECTION_ID) REFERENCES sub_section(ID))");
        db.execSQL("create table answers (ID INTEGER PRIMARY KEY AUTOINCREMENT, ANSWER TEXT, ANSWER_DESCRIPTION TEXT, QUESTION_ID INTEGER, FOREIGN KEY(QUESTION_ID) REFERENCES questions(ID))");
        db.execSQL("create table options (ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION_ID INTEGER, OPTION1 TEXT, OPTION2 TEXT, OPTION3 TEXT, OPTION4 TEXT, FOREIGN KEY(QUESTION_ID) REFERENCES questions(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {

        db.execSQL("DROP TABLE IF EXISTS sections");
        db.execSQL("DROP TABLE IF EXISTS sub_section");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS answers");
        db.execSQL("DROP TABLE IF EXISTS options");
        onCreate(db);

    }
}
