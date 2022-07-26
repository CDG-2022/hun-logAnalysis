package com.cdg;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class Printer {
    private final LogParser logParser;


    public void print(){
        Map<String,Integer> keyMap=logParser.getKeyMap();
        Map<String,Integer> codeMap=logParser.getCodeMap();
        Map<String,Integer> urlMap=logParser.getUrlMap();
        Map<String,Integer> webMap=logParser.getWebMap();
        Map<String,Integer> timeMap=logParser.getTimeMap();

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
        repeatPrint(urlMap, keySet2,3);


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

    private void repeatPrint(Map<String, Integer> urlMap, List<String> keySet2,int repeat) {
        for(int x=0;x<repeat;x++){
            System.out.println(keySet2.get(x)+" "+ urlMap.get(keySet2.get(x)));
        }
        System.out.println();
    }
}
