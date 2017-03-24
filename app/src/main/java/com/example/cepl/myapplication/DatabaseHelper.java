package com.example.cepl.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by CEPL on 09-02-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "svp.db";
    public static final String TABLE_NAME = "svp2_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MOBILE_NO";

    private static String DB_PATH = "/data/data/com.example.cepl.myapplication/databases/";
    private static String DB_NAME = "svp.db";
    private SQLiteDatabase myDataBase;
    //private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, null, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db, context);

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        } else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {

                copyDataBase(context);

            } catch (Exception e) {

                throw new Error("Error copying database");

            }
        }
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

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.
        }
        if(checkDB != null){

            checkDB.close();

        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase(Context myContext)
    {
        Log.i("DB",
                "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try
        {
        myInput = myContext.getAssets().open("svp.db");
        // transfer bytes from the inputfile to the
        // outputfile
        myOutput =new FileOutputStream(DB_PATH+ "svp.db");
        while((length = myInput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }
        myOutput.close();
        myOutput.flush();
        myInput.close();
        Log.i("DB",
                "New Database has been copied to device!");


    }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
