package com.inspire12.practice.api.config;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Profile({"dev","live"})
@Configuration
public class LogConfig {

    @Bean
    public WebServerFactoryCustomizer embeddedServletContainerCustomizer() {


        return container -> {
            if (container instanceof TomcatServletWebServerFactory) {
                ((TomcatServletWebServerFactory) container).addContextCustomizers(context -> {
                    LogbackValve valve = new LogbackValve();
                    valve.setFilename("logback/logback-access.xml");
                    context.getPipeline().addValve(valve);
                });
            }
        };
    }
}
