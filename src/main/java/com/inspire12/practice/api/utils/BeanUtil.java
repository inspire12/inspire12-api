package com.inspire12.practice.api.utils;

import com.inspire12.practice.api.config.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

public class BeanUtil {

    public static <T> T getBean(Class<T> clazz) {

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            throw new NullPointerException("Spring is not initialized");
        }
        return applicationContext.getBean(clazz);
    }

}