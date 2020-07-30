package com.example.teachingdemo.applist;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author sjc
 * @Date 2020/5/19.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class ApplistMatch {

    public static List<String> matchApplist(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("app_list.txt");
            int length = inputStream.available();
            if (length <= 0) {
                return null;
            }

            InputStream inputStreamN = context.getAssets().open("needMatchList.txt");
            int lengthN = inputStreamN.available();
            if (lengthN <= 0) {
                return null;
            }

            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            inputStream.close();
            String result = new String(buffer, "UTF-8");
            String[] results = result.split("\n");


            byte[] bufferN = new byte[lengthN];
            inputStreamN.read(bufferN);
            inputStreamN.close();
            String resultN = new String(bufferN, "UTF-8");
            String[] resultsN = resultN.split("\r\n");

            List<String> needList = new ArrayList<>();
            needList.addAll(Arrays.asList(resultsN));

            for (String needName : needList) {
                String print = needName + " , , , , , , , , , , , , ";
                for (int i = 0; i < results.length; i ++) {
                    String[] aaa = results[i].split("=")[1].split(",");
                    for (String ccc : aaa) {
                        if (ccc.equals(needName)) {
                            String[] ddd = print.split(",");
                            ddd[i+1] = "√";

                            print = Arrays.toString(ddd);
                        }
                    }
                }

                Log.e("matchApplist", print.replace("[", "").replace("]", ""));
            }

        } catch (Exception e) {
            Log.e("ApplistMatch", e.toString());
        }
        return null;
    }
}
