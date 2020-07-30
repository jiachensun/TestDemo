package com.sjc.arouter_lib;

import java.util.HashMap;
import java.util.Map;

public class Arouter {

    private volatile static Arouter instance = null;

    public static Arouter getInstance() {
        if (instance == null) {
            synchronized (Arouter.class) {
                if (instance == null) {
                    instance = new Arouter();
                }
            }
        }
        return instance;
    }

    private Map<String, Class<?>> allActivity;

    Arouter() {
        allActivity = new HashMap<>();
    }

    public void setActivity(String key, Class<?> activity) {
        allActivity.put(key, activity);
    }

    public Class<?> getActivity(String key) {
        return allActivity.get(key);
    }
}