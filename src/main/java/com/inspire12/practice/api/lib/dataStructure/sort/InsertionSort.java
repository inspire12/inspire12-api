package com.inspire12.practice.api.lib.dataStructure.sort;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    public static List<Integer> sort(List<Integer> list) {
        List<Integer> sortedSubList = new ArrayList<>();
        if (list.isEmpty()) {
            return list;
        }
        sortedSubList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int value = list.get(i);
            sortedSubList.add(getIndex(sortedSubList, value), value);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        return sortedSubList;
    }

    public static int getIndex(List<Integer> sortedSubList, int value) {
        int left = 0;
        int right = sortedSubList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (value < sortedSubList.get(mid)) {
                right = mid - 1;
            } else if (value > sortedSubList.get(mid)) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
