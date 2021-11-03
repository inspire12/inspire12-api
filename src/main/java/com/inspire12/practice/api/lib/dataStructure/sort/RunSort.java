package com.inspire12.practice.api.lib.dataStructure.sort;

import java.util.Arrays;
import java.util.List;

public class RunSort {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3,2,1,5,12,31);
        System.out.println(MergeSort.sort(list));
        System.out.println(QuickSort.sort(list));
        list = Arrays.asList(1,1,1,1,12,31);
        System.out.println(InsertionSort.sort(list));
    }
}
