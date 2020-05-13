package com.vitaliis.interview.controllers;

import com.vitaliis.interview.dao.DataRepository;
import com.vitaliis.interview.model.FileRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExternalApiController {
    private final DataRepository repository;

    @Autowired
    public ExternalApiController(DataRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping("/id/{id}")
    public void deleteRaw(@PathVariable("id") String id) {
        repository.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public Optional<FileRow> getRaw(@PathVariable("id") String id) {
         return repository.findById(id);
    }
}
