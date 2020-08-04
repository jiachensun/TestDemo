package com.example.teachingdemo.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @Author sjc
 * @Date 2020/7/31
 * Description：
 */
@Database(entities = {UserDatabase.class}, version = 1)
public abstract class UserDatabaseDelegate extends RoomDatabase {
    private static UserDatabaseDelegate INSTANCE = null;

    public abstract UserDao getUserDao();

    public static UserDatabaseDelegate getUserDelegate(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabaseDelegate.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabaseDelegate.class, "test_database.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
