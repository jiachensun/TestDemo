package com.example.teachingdemo.rsa;

import android.util.SparseArray;

import androidx.core.util.Pools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sjc
 * @Date 2020/7/28
 * Description：二分查找
 * 前提：数组或链表内必须是连续的
 * 思路：从中间开始查找，判断当前值与中间值的大小，用递归方式找到左边还是右边
 */
public class BinarySearch {
    private int[] intArray = {2,3,4,5,6,7,8,9,10};

    @Test
    public void main() {
        System.out.println("result = " + binarySearch(intArray, intArray.length, 5));
    }

    private int binarySearch(int[] array, int size, int value) {
        int start = 0;
        int end = size - 1;
        while (start <= end) {
            int middleIndex = (start + end) >>> 1;
            int middleValue = array[middleIndex];
            if (middleValue > value) {
                end = middleIndex - 1;
            } else if (middleValue < value) {
                start = middleIndex + 1;
            } else {
                return middleIndex;
            }
        }

        return ~start;
    }
}
