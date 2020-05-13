package com.vitaliis.interview.service;

import com.vitaliis.interview.dao.DataRepository;
import com.vitaliis.interview.model.FileRow;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileService {

    final static int BATCH_SIZE = 100;
    final static int ROW_LENGTH = 4;

    private final DataRepository rep;
    private final ValidationService validationService;

    private List<FileRow> fileRows = new LinkedList<>();

    @Autowired
    public FileService(DataRepository rep, ValidationService validationService) {
        this.rep = rep;
        this.validationService = validationService;
    }

    public String processFile(MultipartFile file) {

        try (Scanner scanner = new Scanner(file.getInputStream())) {

            String headerLine = scanner.nextLine();
            if (!validationService.validateHeader(headerLine)) {
                return "Invalid header";
            }

            for (String line = scanner.nextLine(); scanner.hasNextLine() && !Strings.EMPTY.equals(line); line = scanner.nextLine()) {

                List<String> row = Stream.of(line.split(","))
                        .map(String::trim)
                        .limit(ROW_LENGTH)
                        .collect(Collectors.toList());

                if (!validationService.validate(row)) {
                    continue;
                }

                FileRow myValue = new FileRow(row.get(0), row.get(1), row.get(2), row.get(3));
                fileRows.add(myValue);

                if(fileRows.size() > BATCH_SIZE){
                    rep.saveAll(fileRows);
                    fileRows.clear();
                }
            }
            rep.saveAll(fileRows);

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return "ok";
    }

}
