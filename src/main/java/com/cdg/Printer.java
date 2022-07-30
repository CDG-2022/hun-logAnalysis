package com.cdg;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Printer {
    private final LogParser logParser;
    BufferedWriter writer;


    public void print() throws IOException {
        File log = new File("./output.log");
        if(!log.exists()){
            log.createNewFile();
        }
        writer = new BufferedWriter(new FileWriter(log,false));

        Map<String,Integer> keyMap=logParser.getKeyMap();
        Map<String,Integer> codeMap=logParser.getCodeMap();
        Map<String,Integer> urlMap=logParser.getUrlMap();
        Map<String,Integer> webMap=logParser.getWebMap();
        Map<String,Integer> timeMap=logParser.getTimeMap();

        List<String> keySet1 = new ArrayList<>(keyMap.keySet());
        keySet1.sort(((o1, o2) -> keyMap.get(o2).compareTo(keyMap.get(o1))));
        writer.write("최다호출 APIKEY\n"+keySet1.get(0)+" "+keyMap.get(keySet1.get(0))+"회");
        writer.newLine();writer.newLine();

        writer.write("상태코드 별 횟수");
        writer.newLine();
        for (String key:codeMap.keySet()){
            writer.write(key+" : "+codeMap.get(key));
            writer.newLine();
        }
        writer.newLine();

        writer.write("상위 3개의 API ServiceID와 각각의 요청 수");
        writer.newLine();
        List<String> keySet2 = new ArrayList<>(urlMap.keySet());
        keySet2.sort(((o1, o2) -> urlMap.get(o2).compareTo(urlMap.get(o1))));
        repeatPrint(urlMap, keySet2,3);


        writer.write("피크 시간대");
        writer.newLine();
        List<String> keySet3 = new ArrayList<>(timeMap.keySet());
        keySet3.sort(((o1, o2) -> timeMap.get(o2).compareTo(timeMap.get(o1))));
        writer.write(keySet3.get(0)+" "+timeMap.get(keySet3.get(0))+"호출");
        writer.newLine();writer.newLine();

        writer.write("웹 브라우저 별 사용비율");
        writer.newLine();
        int mo=0;
        for (String key:webMap.keySet()){
            mo+=webMap.get(key);
        }
        for (String key:webMap.keySet()){
            writer.write(key+" "+((double)webMap.get(key)/mo*100)+"%");
            writer.newLine();
        }
        writer.close();
    }

    private void repeatPrint(Map<String, Integer> urlMap, List<String> keySet2,int repeat) throws IOException {
        for(int x=0;x<repeat;x++){
            writer.write(keySet2.get(x)+" "+ urlMap.get(keySet2.get(x)));
            writer.newLine();
        }
        writer.newLine();
    }
}
