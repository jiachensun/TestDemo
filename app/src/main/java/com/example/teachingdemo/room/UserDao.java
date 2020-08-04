package com.example.teachingdemo.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * @Author sjc
 * @Date 2020/7/31
 * Description：
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM UserDatabase")
    List<UserDatabase> getAll();

    @Query("select * from userdatabase where user_name like :name")
    UserDatabase getUserByName(String name);

    // 直接数据库标识所有的个数，要是有条件可放入括号内select count(*) from （条件查询）
    @Query("select count(*) from UserDatabase")
    int getAllUserCount();

    @Insert
    void insertUser(UserDatabase user);
}
