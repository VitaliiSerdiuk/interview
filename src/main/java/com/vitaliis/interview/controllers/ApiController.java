package com.vitaliis.interview.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@Slf4j
@RestController
public class ApiController {

    @GetMapping("/index")
    public ModelAndView index() {

        return new ModelAndView("/index.html");
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String[] mandatoryHeaderKeyword = {"PRIMARY_KEY", "NAME", "DESCRIPTION", "UPDATED_TIMESTAMP"};

        try (Scanner scanner = new Scanner(file.getInputStream())) {

            String headerLine = scanner.nextLine();
            if(!Arrays.stream(mandatoryHeaderKeyword).allMatch(headerLine::contains)){
                return "Invalid header";
            }

            String nextLine = scanner.nextLine();
            while (scanner.hasNextLine() && !Strings.EMPTY.equals(nextLine)) {



                nextLine = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
