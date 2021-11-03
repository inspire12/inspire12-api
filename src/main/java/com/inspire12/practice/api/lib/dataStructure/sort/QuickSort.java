package com.inspire12.practice.api.lib.dataStructure.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static List<Integer> sort(List<Integer> list) {
        return quickSort(list);
    }

    private static List<Integer> quickSort(List<Integer> list) {
        System.out.println(list);
        if (list.size() <= 1) {
            return list;
        }
        int partition = list.get(getPartitionIndex(list));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> equals = new ArrayList<>();
        for(int value : list ) {
            if (value < partition) {
                left.add(value);
            } else if (value > partition) {
                right.add(value);
            } else {
                equals.add(value);
            }
        }

        List<Integer> result = quickSort(left);
        result.addAll(equals);
        result.addAll(quickSort(right));

        return result;
    }

    private static int getPartitionIndex(List<Integer> list) {
        return list.size() / 2;
    }
}
