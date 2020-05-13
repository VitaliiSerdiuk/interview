package com.vitaliis.interview.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ValidationService {
    private final String[] MANDATORY_HEADER_KEY_WORD = {"PRIMARY_KEY", "NAME", "DESCRIPTION", "UPDATED_TIMESTAMP"};

    public boolean validateHeader(String headerLine) {
        return Arrays.stream(MANDATORY_HEADER_KEY_WORD).allMatch(headerLine::contains);
    }

    public boolean validate(List<String> row) {
        return row.size() == 4 && row.stream().allMatch(StringUtils::isNotBlank);
    }
}
