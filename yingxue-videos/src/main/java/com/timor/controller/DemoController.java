package com.timor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("demos")
public class DemoController {

    @GetMapping
    public String demos(){
        log.info("video demos");
        return "video demos";

    }

}
