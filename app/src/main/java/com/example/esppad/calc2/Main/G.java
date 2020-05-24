package com.example.esppad.calc2.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.esppad.calc2.Main.Tools.passwordHelper.PassHelperDatabaseHelper;

import java.io.File;


public class G {
    public static G app;
    private Context currentContext;
    private SharedPreferences sharedPreferences;
    private String sharedPreferenceKey;
    private String databaseName;
    private static SQLiteDatabase database;
    private PassHelperDatabaseHelper dbHelper;

    public G(Context context,String sharedPreferenceKey,String databaseName){
        currentContext = context;
        this.sharedPreferenceKey = sharedPreferenceKey;
        sharedPreferences = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE);
        this.databaseName = databaseName;
    }

    public String getSharedPreferenceValue(){
        return sharedPreferences.getString(sharedPreferenceKey,null);
    }
    public boolean setSharedPreferenceValue(String value){
        boolean flag = false;

        return flag;
    }

    public void createPasswordsAppDirectories() {
        File dbDir = new File(currentContext.getFilesDir(),databaseName);

        if (!dbDir.exists()) {
            boolean wasCreated = dbDir.mkdirs();
            Toast.makeText(currentContext, "Already made",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(currentContext, "Not Made",Toast.LENGTH_LONG).show();
        }

    }

    public int postPassword(String url,String user,String password){
        int autoNum = 0;
        if(database==null){
            autoNum = 0;
        }else{
            if(dbHelper.postPassword(database,url,user,password)){
                String query = "SELECT * FROM passwords";
                try {
                    Cursor cursor = database.rawQuery(query,null);
                    cursor.moveToLast();
                    autoNum = cursor.getInt(0);
                }catch (Exception e){
                    Log.i("Log1","calculate auto id has been failed: "+ e.toString());
                }
            }else{
                return autoNum = 0;
            }
        }
        return autoNum;
    }

    public boolean deletePassword(int id){
        if(id>0){
            if(dbHelper.deletePassword(database,id)){
                return true;
            }
        }
        return false;
    }

    public void createOrOpenDataBase(){
        if(database!=null){
            return;
        }
        dbHelper = new PassHelperDatabaseHelper(currentContext);
        database = dbHelper.getWritableDatabase();
    }
}
