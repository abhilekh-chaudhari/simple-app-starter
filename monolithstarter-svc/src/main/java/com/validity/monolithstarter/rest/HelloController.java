package com.validity.monolithstarter.rest;

import com.validity.monolithstarter.service.HelloService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Inject
    private HelloService helloService;

    @CrossOrigin(origins = "*")
    @GetMapping("/hello")
    public String getHelloMessage() {
        return helloService.getHelloMessage();
    }
}
