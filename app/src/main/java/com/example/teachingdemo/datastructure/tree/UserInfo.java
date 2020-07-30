package com.example.teachingdemo.datastructure.tree;

/**
 * @Author sjc
 * @Date 2020/6/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class UserInfo implements Comparable<UserInfo> {

    public String id;
    public String name;
    public int age;

    public UserInfo(String id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(UserInfo o) {
        // 0：顺序不变 1：正序  -1：倒序
        // 每次put的时候都要比较，这样就可以排序了
        return Integer.compare(this.age, o.age);
    }
}
