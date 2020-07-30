package com.example.teachingdemo.design_mode.share_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class MyPeople {

    private static final int MAX_COUNT = 5;
    private static int usedCount = 0;
    private static PeopleMode next;

    public static PeopleMode obtain() {
        if (usedCount <= MAX_COUNT && usedCount > 0) {

        }
        return createNew();
    }

    public static PeopleMode createNew() {
        PeopleMode peopleMode = new PeopleMode();
        usedCount ++;
        return peopleMode;
    }

    public void removeMessage(MyPeople message) {

    }
}
