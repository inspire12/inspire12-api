package com.inspire12.practice.api.sample.lib.ds.sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static List<Integer> sort(List<Integer> list) {

        return splitForMerge(list);
    }

    private static List<Integer> splitForMerge(List<Integer> subList) {
        int mid = subList.size() / 2;
        if (subList.size() <= 1) {
            return subList;
        }

        List<Integer> left = splitForMerge(subList.subList(0, mid));
        List<Integer> right = splitForMerge(subList.subList(mid, subList.size()));

        return merge(left, right);
    }

    private static void print(Object object) {
        System.out.println(object);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            int leftValue = left.get(leftIndex);
            int rightValue = right.get(rightIndex);

            if (leftValue <= rightValue) {
                result.add(leftValue);
                leftIndex++;
            } else {
                result.add(rightValue);
                rightIndex++;
            }
        }
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }
        return result;
    }
}
