package com.cdg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;

@Getter
@RequiredArgsConstructor
public class LogFileReader {
    private final LogParser parser;

    public void read() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.log"));
        parser.parse(reader);
    }
}
