package com.vitaliis.interview.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ApiController {

    @PostMapping("file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        return "ok";
    }
}
