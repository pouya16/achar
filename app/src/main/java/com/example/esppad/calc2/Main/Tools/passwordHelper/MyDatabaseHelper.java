package com.example.esppad.calc2.Main.Tools.passwordHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "password";
    public static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, context.getFilesDir() + "/" + DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createPasswordsTable(db);
        createPrivateReport(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            createPasswordsTable(db);
            createPrivateReport(db);
        }
        if(newVersion == 3){
            createPasswordsTable(db);
            createPrivateReport(db);
        }
    }



    public void createPasswordsTable(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'passwords' (" +
                        //0:
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'url' TEXT UNIQUE , " +
                        "'password' TEXT UNIQUE " +
                        ")";

        db.execSQL(query);
    }


    public void createPrivateReport(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'privateReport' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'password' INTEGER UNIQUE " +
                        ")";

        db.execSQL(query);
    }

    public void postPrivateReport(SQLiteDatabase db,String url,String password){
        try {
            String query = "INSERT INTO 'passwords'('url','password') values " +
                    "('" + url + "', '" + password + "')";
            db.execSQL(query);
            Log.i("Log", "private message successfully added");
        }catch (Exception e){
            Log.e("Log",""+e.getMessage());
            Log.i("Log", "Failed to add private message");
        }
    }
}