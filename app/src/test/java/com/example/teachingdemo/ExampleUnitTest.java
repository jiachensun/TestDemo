package com.example.teachingdemo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void nativeSort() {
        int[] abc = new int[10000];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; i ++) {
            int randomNum = random.nextInt(10000);
            abc[i] = randomNum;
        }
        long beginTime = System.currentTimeMillis();
        Arrays.sort(abc);
        System.out.println("原生排序时长 = " + (System.currentTimeMillis() - beginTime));
        for (int a : abc) {
            System.out.println("a = " + a);
        }
    }

    @Test
    public void quickSort() {
        int[] abc = new int[10000];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; i ++) {
            int randomNum = random.nextInt(10000);
            abc[i] = randomNum;
        }
        long beginTime = System.currentTimeMillis();
        sortByStandard(abc, 0, abc.length -1);
        System.out.println("快速排序时长 = " + (System.currentTimeMillis() - beginTime));
        for (int a : abc) {
            System.out.println("a = " + a);
        }
    }

    /**
     * 获得基准值的位置
     */
    private int getStandardIndex(int[] abc, int left, int right) {
        int middleIndex = left + 1;
        int standard = abc[left];

        // 找到基准值的位置
        for (int i = middleIndex; i <= right; i++) {
            if (standard > abc[i]) {
                int temp = abc[i];
                abc[i] = standard;
                abc[middleIndex] = temp;
                middleIndex ++;
            }
        }
        // 将基准值放入到middleindex上
        int temp = abc[middleIndex - 1];
        abc[middleIndex - 1] = standard;
        abc[left] = temp;
        return middleIndex;
    }

    /**
     * 根据基准值排序
     */
    private void sortByStandard(int[] abc, int left, int right) {
        // 递归，当左边值小于右边值，说明还没有递归完
        if (left < right) {
            // 找到基准值
            int standard = getStandardIndex(abc, left, right);
            // 递归排序基准值左边的数组
            sortByStandard(abc, left, standard - 1);
            // 递归排序基准值右边的数组
            sortByStandard(abc, standard + 1, right);
        }
    }

    @Test
    public void bubbleSort() {
        int[] abc = new int[10000];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; i ++) {
            int randomNum = random.nextInt(10000);
            abc[i] = randomNum;
        }
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < abc.length - 1; i ++) {
            for (int j = i + 1; j < abc.length; j ++) {
                if (abc[i] > abc[j]) {
                    int temp = abc[i];
                    abc[i] = abc[j];
                    abc[j] = temp;
                }
            }
        }
        System.out.println("冒泡排序时长 = " + (System.currentTimeMillis() - beginTime));
        for (int a : abc) {
            System.out.println("a = " + a);
        }
    }
}