package com.inspire12.practice.api.sample.lib.ds.stringSearch;

import java.util.List;

public class RunStringSearch {
    public static void main(String[] args) {
        String fullText = "ABABABABBABABABABC";
        String match = "ABABABABC";

        List<Integer> index = Kmp.run(match, fullText);
        System.out.println(index);
    }
}
