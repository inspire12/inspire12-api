package com.inspire12.practice.api.web.socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

@Controller
public class SocketController {


    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(IllegalArgumentException e) {
        var message = ("an exception occurred! " + NestedExceptionUtils.getMostSpecificCause(e));
        System.out.println(message);
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    GreetingResponse greet(GreetingRequest request) throws Exception {
        Assert.isTrue(Character.isUpperCase(request.getName().charAt(0)), () -> "the name must start with a capital letter!");
        Thread.sleep(1_000);
        return new GreetingResponse("Hello, " + request.getName());
    }

    @Data
    @AllArgsConstructor
    public static class GreetingResponse {
        String message;
    }

    @Data
    public static class GreetingRequest {
        String name;
    }
}


