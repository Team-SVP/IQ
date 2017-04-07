package com.example.cepl.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by CEPL on 09-02-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "svp.db";
    public static final String TABLE_NAME = "svp_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MOBILE_NO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    /*  db.execSQL("DROP TABLE IF EXISTS sections");
        db.execSQL("DROP TABLE IF EXISTS sub_section");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS answers");
        db.execSQL("DROP TABLE IF EXISTS options");

        db.execSQL("create table sections (ID INTEGER PRIMARY KEY AUTOINCREMENT, SECTION_NAME TEXT)");
        db.execSQL("create table sub_section (ID INTEGER PRIMARY KEY AUTOINCREMENT, SECTION_ID INTEGER, SUB_SECTION_NAME TEXT, FOREIGN KEY(SECTION_ID) REFERENCES sections(ID))");
        db.execSQL("create table questions (ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION_DETAIL TEXT, DIFFICULTY_LEVEL TEXT, SECTION_ID INTEGER, SUB_SECTION_ID INTEGER, FOREIGN KEY(SECTION_ID) REFERENCES section(ID), FOREIGN KEY(SUB_SECTION_ID) REFERENCES sub_section(ID))");
        db.execSQL("create table answers (ID INTEGER PRIMARY KEY AUTOINCREMENT, ANSWER TEXT, ANSWER_DESCRIPTION TEXT, QUESTION_ID INTEGER, FOREIGN KEY(QUESTION_ID) REFERENCES questions(ID))");
        db.execSQL("create table options (ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION_ID INTEGER, OPTION1 TEXT, OPTION2 TEXT, OPTION3 TEXT, OPTION4 TEXT, FOREIGN KEY(QUESTION_ID) REFERENCES questions(ID))");

        db.execSQL("INSERT INTO 'sections' ('SECTION_NAME') VALUES ('Test_IQ')");
        db.execSQL("INSERT INTO 'sub_section' ('SECTION_ID','SUB_SECTION_NAME') VALUES (1, 'Windows')");
        db.execSQL("INSERT INTO 'sub_section' ('SECTION_ID','SUB_SECTION_NAME') VALUES (1, 'Linux')");
        db.execSQL("INSERT INTO 'sub_section' ('SECTION_ID','SUB_SECTION_NAME') VALUES (1, 'Vmware')");

        db.execSQL("INSERT INTO 'questions' ('QUESTION_DETAIL','DIFFICULTY_LEVEL','SECTION_ID','SUB_SECTION_ID') VALUES ('Test que','low',1,2)");
        db.execSQL("INSERT INTO 'questions' ('QUESTION_DETAIL','DIFFICULTY_LEVEL','SECTION_ID','SUB_SECTION_ID') VALUES ('Test que2','low',1,2)");

        db.execSQL("INSERT INTO 'answers' ('ANSWER','ANSWER_DESCRIPTION','QUESTION_ID') VALUES (1, 'Answer for q1', 1)");
        db.execSQL("INSERT INTO 'answers' ('ANSWER','ANSWER_DESCRIPTION','QUESTION_ID') VALUES (2, 'Answer for q2', 2)");

        db.execSQL("INSERT INTO 'options' ('QUESTION_ID','OPTION1','OPTION2','OPTION3','OPTION4') VALUES (1, '1_1', '1_2', '1_3', '1_4')");
        db.execSQL("INSERT INTO 'options' ('QUESTION_ID','OPTION1','OPTION2','OPTION3','OPTION4') VALUES (2, '2_1', '2_2', '2_3', '2_4')");
    */
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

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from sections", null);
        return res;

    }

    public Cursor getSubSectionData(String sec_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from sub_section where SECTION_ID =(select ID from sections where SECTION_NAME = \"" + sec_name + "\")", null);
        return res;
    }

    public Cursor getQuestionData(String sec_name, String subsec_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT P.*, H.OPTION1, H.OPTION2, H.OPTION3, H.OPTION4, H.QUESTION_ID FROM questions P INNER JOIN options H on (P.ID = H.QUESTION_ID) where SECTION_ID = (select ID from sections where SECTION_NAME= \"" + sec_name + "\") and SUB_SECTION_ID = (select ID from sub_section where SUB_SECTION_NAME= \"" + subsec_name + "\")", null);
        return res;

    }

    public Cursor getAnswerData(String questionid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from answers where QUESTION_ID= \"" + questionid + "\"", null);
        return res;

    }

    public Cursor tableExists(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select name from sqlite_master where name = 'sections'", null);
        return res;

    }

}