package com.inspire12.practice.api.support.utils;

public class Inspire12MathUtils {
    public static double getEloExpectWinRatio(double op, double me) {
        return 1d / (Math.pow(10d, ((op - me) / 400d)) + 1d);
    }
}
