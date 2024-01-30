package com.rushi.todo_List.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rushi.todo_List.database.parameter.Parameters;
import com.rushi.todo_List.database.taskModuling.TaskModuling;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class MySqliteDB extends SQLiteOpenHelper {

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Parameters.TABLE_NAME;

    //Create table for the user..to store the value
    private static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + Parameters.TABLE_NAME + " (" +
                    Parameters.KEY_ID + " INTEGER PRIMARY KEY," +
                    Parameters.KEY_TASK + " TEXT)";

    public  MySqliteDB(Context context){
        //Creating a Database For storing..
        super(context, Parameters.DB_NAME,null, Parameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating a Table to store all Data in Row Column formate
        db.execSQL(CREATE_TASK_TABLE);
        Log.d("dbin", "Database Created Successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //If DataBase exits then recreate a databse.
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addTask(TaskModuling taskModuling){
        //inserting a Data in Table..
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //pasing the value Key value Pair..

        values.put(Parameters.KEY_TASK, taskModuling.getTask());
        db.insert(Parameters.TABLE_NAME, null, values);
        Log.d("dbin", "Name is Going to Mysqlite");
        db.close();
    }

    public ArrayList<TaskModuling> getAllTask(){
        //Retrieve all data from Table.
        ArrayList<TaskModuling> allTask = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Making a cursor to travel from table..
        Cursor cursor = db.rawQuery(" SELECT *  FROM "+ Parameters.TABLE_NAME, null);
        //get all data by moving the cursor
        if (cursor.moveToFirst()){
            do {
                allTask.add(new TaskModuling(
                        cursor.getString(1)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("dbin", "fetching From Mysqlite");
        //Return all fetched Data..
        return  allTask;
    }

    public void deleteTask(String task){
        SQLiteDatabase db = getWritableDatabase();
        Log.d("dbin", "I think done");
        db.delete(Parameters.TABLE_NAME, Parameters.KEY_TASK + "=?", new String[]{task});
    }
}
