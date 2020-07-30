package com.example.teachingdemo.hotfix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sjc
 * @Date 2020/6/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class FixClass {

    public String getResult() {

        throw new RuntimeException("你崩溃了");
//        return "加载完成";
    }
}
