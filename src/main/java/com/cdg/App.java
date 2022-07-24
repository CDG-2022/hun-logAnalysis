package com.cdg;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    static HashMap<String,Integer> keyMap= new HashMap<>();
    static HashMap<String,Integer> codeMap= new HashMap<>();
    static HashMap<String,Integer> urlMap= new HashMap<>();
    static HashMap<String,Integer> webMap= new HashMap<>();
    static HashMap<String,Integer> timeMap= new HashMap<>();

    public static void main( String[] args ) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("input.log"));

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