package com.cdg;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class Printer {
    private final LogFileReader logFileReader;


    public void print(){
        HashMap<String,Integer> keyMap=logFileReader.getParser().getKeyMap();
        HashMap<String,Integer> codeMap=logFileReader.getParser().getCodeMap();
        HashMap<String,Integer> urlMap=logFileReader.getParser().getUrlMap();
        HashMap<String,Integer> webMap=logFileReader.getParser().getWebMap();
        HashMap<String,Integer> timeMap=logFileReader.getParser().getTimeMap();

        List<String> keySet1 = new ArrayList<>(keyMap.keySet());
        keySet1.sort(((o1, o2) -> keyMap.get(o2).compareTo(keyMap.get(o1))));
        System.out.println("최다호출 APIKEY\n"+keySet1.get(0)+" "+keyMap.get(keySet1.get(0))+"회");
        System.out.println();

        System.out.println("상태코드 별 횟수");
        for (String key:codeMap.keySet()){
            System.out.println(key+" : "+codeMap.get(key));
        }
        System.out.println();

        System.out.println("상위 3개의 API ServiceID와 각각의 요청 수");
        List<String> keySet2 = new ArrayList<>(urlMap.keySet());
        keySet2.sort(((o1, o2) -> urlMap.get(o2).compareTo(urlMap.get(o1))));
        System.out.println(keySet2.get(0)+" "+urlMap.get(keySet2.get(0)));
        System.out.println(keySet2.get(1)+" "+urlMap.get(keySet2.get(1)));
        System.out.println(keySet2.get(2)+" "+urlMap.get(keySet2.get(2)));
        System.out.println();

        System.out.println("피크 시간대");
        List<String> keySet3 = new ArrayList<>(timeMap.keySet());
        keySet3.sort(((o1, o2) -> timeMap.get(o2).compareTo(timeMap.get(o1))));
        System.out.println(keySet3.get(0)+" "+timeMap.get(keySet3.get(0))+"호출");
        System.out.println();

        System.out.println("웹 브라우저 별 사용비율");
        int mo=0;
        for (String key:webMap.keySet()){
            mo+=webMap.get(key);
        }
        for (String key:webMap.keySet()){
            System.out.println(key+" "+((double)webMap.get(key)/mo*100)+"%");
        }
    }
}
