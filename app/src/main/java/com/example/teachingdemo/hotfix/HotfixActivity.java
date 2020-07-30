package com.example.teachingdemo.hotfix;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.teachingdemo.R;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class HotfixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotfix);
    }

    public void onClickGetResult(View view) {

        loadClassFromLocalFile();
    }

    private void loadClassFromLocalFile() {

        FixClass fixClass = new FixClass();
        Toast.makeText(this, fixClass.getResult(), Toast.LENGTH_SHORT).show();
    }

    private void loadClassFromDex() {

        boolean copyResult = HotFixUtil.copyDexToPrivateDir(this, "Hotfix.dex");
        if (copyResult) {
            DexClassLoader dexClassLoader = HotFixUtil.getDexClassLoader(this, "Hotfix.dex");

            try {
                Class<?> fixC = dexClassLoader.loadClass("com.example.teachingdemo.hotfix.FixClass");
                Log.i("HotfixActivity", "loaded class = " + fixC);
                Method method = fixC.getMethod("getResult");
                method.setAccessible(true);
                Object result = method.invoke(fixC.newInstance());
                Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickLoadDex(View view) {

        loadClassFromDex();
    }
}