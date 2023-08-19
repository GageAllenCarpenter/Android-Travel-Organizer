package edu.wgu.capstone.controller;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

/**
 * Provides a singleton instance of the AppDatabase.
 */
public class DatabaseContext extends Application {
    private static AppDatabase database;

    /**
     * Private constructor to prevent instantiation.
     */
    private DatabaseContext(){}

    /**
     * Returns the singleton instance of the AppDatabase.
     * @param context The context.
     * @return The AppDatabase.
     */
    public static AppDatabase getDatabase(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "vacation.db").build();
        }
        return database;
    }
}
