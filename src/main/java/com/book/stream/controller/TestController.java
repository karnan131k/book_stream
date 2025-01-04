package com.book.stream.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    public void greetings(){
        System.out.println("welcome");
    }
}
