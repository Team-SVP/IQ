package com.example.cepl.myapplication;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by CEPL on 29-03-2017.
 */

public class databaseCopy  {
    //data/user/0/com.example.cepl.myapplication/files/databases/svp.db
    //private static String DB_PATH;
    private static String DB_PATH = "/data/data/com.example.cepl.myapplication/databases/";
    private static String DB_NAME = "svp.db";
    private SQLiteDatabase myDataBase;

    public databaseCopy (Context context) {
        //ContextWrapper cw = new ContextWrapper( context.getApplicationContext());
        //databaseCopy.DB_PATH = cw.getFilesDir().getAbsolutePath()+ "/databases/"; //edited to databases
        //if(android.os.Build.VERSION.SDK_INT >= 17) {
          //  databaseCopy.DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        //} else {
          //  databaseCopy.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        //}
        //boolean dbExist = checkDataBase();
        boolean dbExist = false;

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            //getReadableDatabase();
            try {

                copyDataBase(context);

            } catch (Exception e) {

                throw new Error("Error copying database");

            }
        }
    }

    private void copyDataBase(Context myContext) {
        Log.i("DB", "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = myContext.getAssets().open("svp.db");
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(DB_PATH + "svp.db");
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("DB", "New Database has been copied to device!");


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(myContext, "Error." + e,
                    Toast.LENGTH_SHORT).show();
        }
    }


    private boolean checkDataBase(){

        //SQLiteDatabase checkDB = null;
        boolean checkDB = false;
        try{
            String myPath = DB_PATH + DB_NAME;
            //checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            File f = new File(myPath);
            if(f.exists()) {
                checkDB = true;
            } else {
                checkDB = false;
            }
        }catch(Exception e){

            //database does't exist yet.
        }
        /*
        if(checkDB != null){

            checkDB.close();

        } */
        //return checkDB != null ? true : false;
        return (checkDB);
    }
}
