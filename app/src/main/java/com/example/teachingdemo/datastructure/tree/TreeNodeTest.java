package com.example.teachingdemo.datastructure.tree;

import android.util.Log;

import java.util.Comparator;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author sjc
 * @Date 2020/6/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class TreeNodeTest {

    /**
     * 根据前序遍历和中序遍历重新构建二叉树
     */
    public static void getTreeMap() {

        TreeMap<UserInfo, String> userTreeMap = new TreeMap<>();

        userTreeMap.put(new UserInfo("001", "张三", 20), "001");
        userTreeMap.put(new UserInfo("002", "李四", 19), "002");
        userTreeMap.put(new UserInfo("003", "王五", 25), "003");

        for (UserInfo userInfo : userTreeMap.keySet()) {
            Log.i("TreeNodeTest", Objects.requireNonNull(userInfo).toString());
        }
    }
}
