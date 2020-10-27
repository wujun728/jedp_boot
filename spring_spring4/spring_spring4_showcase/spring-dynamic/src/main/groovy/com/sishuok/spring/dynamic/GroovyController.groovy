package com.sishuok.spring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-18
 * <p>Version: 1.0
 */
@RestController
class GroovyController {

    @Autowired
    private UserController userController;

    @RequestMapping("/groovy")
    public String hello() {
        println(userController.hello());
        return "hello";
    }

}
