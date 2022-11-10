package com.inspire12.practice.api.presentation.controller.api;




import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ApiControllerTest {

    @Test
    void test1() {
        double a = 1101;
        double b = 1101;

        double result = 1d / (Math.pow(10d, ((b-a)/400d)) + 1d);
        assertThat(result).isEqualTo(0.5);
    }

    @Test
    void test2() {
        double a = 1101;
        double b = 1101;

        double result = 1d / (Math.pow(10d, ((b-a)/400d)) + 1d);
        assertThat(result).isEqualTo(0.5);
    }
}