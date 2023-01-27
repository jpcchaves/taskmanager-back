package com.taskmanager.springboot.taskmanager.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloController {


    @GetMapping(value = "/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are u learning today?";
    }
}
