package com.example.teachingdemo.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author sjc
 * @Date 2020/7/31
 * Description：
 */
@Entity
public class UserDatabase {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    @ColumnInfo
    private String user_name;

    @ColumnInfo
    private String user_sex;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }
}
