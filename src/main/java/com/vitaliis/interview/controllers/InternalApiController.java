package com.vitaliis.interview.controllers;

import com.vitaliis.interview.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
public class InternalApiController {

    private final FileService processFileService;

    @Autowired
    public InternalApiController(FileService processFileService) {
        this.processFileService = processFileService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/index.html");
    }

    @PostMapping("/file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        return processFileService.processFile(file);
    }
}
