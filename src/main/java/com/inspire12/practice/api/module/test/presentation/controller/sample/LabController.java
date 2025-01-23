package com.inspire12.practice.api.module.test.presentation.controller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lab")
@RestController
public class LabController {

    @GetMapping
    public String hi() throws InterruptedException {
        int i = 100;
        while (i > 0) {
//            Thread t = new Thread(, () -> System.out.println("hi"));

//            t.start();
            i--;
        }
        return "hi";
    }
}
