package com.cdg;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

@Getter
public class LogParser {
    private final HashMap<String,Integer> keyMap= new HashMap<>();
    private final HashMap<String,Integer> codeMap= new HashMap<>();
    private final HashMap<String,Integer> urlMap= new HashMap<>();
    private final HashMap<String,Integer> webMap= new HashMap<>();
    private final HashMap<String,Integer> timeMap= new HashMap<>();


    public void parse(BufferedReader reader) throws IOException {
        while (true){
            String[] strings = StringUtils.substringsBetween(reader.readLine(), "[", "]");
            if (strings==null) break;

            codeMap.put(strings[0],codeMap.getOrDefault(strings[0],0)+1);

            strings[1]=StringUtils.removeStart(strings[1],"http://apis.daum.net/search/");
            String sub=StringUtils.substringBetween(strings[1],"apikey=","q");

            keyMap.put(sub,keyMap.getOrDefault(sub,0)+1);

            if(strings[1].contains("?")) {
                String[] split = StringUtils.split(strings[1], "?");
                urlMap.put(split[0], urlMap.getOrDefault(split[0], 0) + 1);
            }

            webMap.put(strings[2],webMap.getOrDefault(strings[2],0)+1);

            timeMap.put(strings[3],timeMap.getOrDefault(strings[3],0)+1);
        }
    }

}
