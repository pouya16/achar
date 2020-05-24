package com.example.esppad.calc2.Main.Tools.passwordHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PassHelperDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "password";
    public static final int DB_VERSION = 1;

    public PassHelperDatabaseHelper(Context context) {
        super(context, context.getFilesDir() + "/" + DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createPasswordsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            createPasswordsTable(db);
        }
        if(newVersion == 3){
            createPasswordsTable(db);
        }
    }



    public void createPasswordsTable(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'passwords' (" +
                        //0:
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'url' TEXT , " +
                        "'user' TEXT , " +
                        "'password' TEXT " +
                        ")";

        db.execSQL(query);
    }

    public boolean postPassword(SQLiteDatabase db,String urlText,String username,String postPassword){
        boolean flag = false;
        ContentValues values = new ContentValues();
        values.put("url",urlText);
        values.put("user",username);
        values.put("password",postPassword);
        try{
            db.insert("passwords",null,values);
            flag = true;
            db.close();
        }catch (Exception e){
            Log.i("Log1","insert failed with exception: " + e.toString());
        }
        return flag;
    }
    public boolean deletePassword(SQLiteDatabase db,int id){
        boolean flag = false;
        try {
            String query = "DELETE FROM passwords WHERE autoId = " + id;
            db.execSQL(query);
            flag = true;
            db.close();
        }catch (Exception e){
            Log.i("Log1","Delete failed with exception: " + e.toString());
        }
        return flag;
    }

}