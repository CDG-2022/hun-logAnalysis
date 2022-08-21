package com.cdg;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
public class LogParser {
    private static final String FIXED_PREFIX_URL ="http://apis.daum.net/search/";

    private final Map<String,Integer> keyMap= new HashMap<>();
    private final Map<String,Integer> codeMap= new HashMap<>();
    private final Map<String,Integer> urlMap= new HashMap<>();
    private final Map<String,Integer> webMap= new HashMap<>();
    private final Map<String,Integer> timeMap= new HashMap<>();


    public void parse(BufferedReader reader) throws IOException {
        while (true){
            String[] splitedLog = StringUtils.substringsBetween(reader.readLine(), "[", "]");
            if (splitedLog==null) { break;}
            String httpStatusCode=splitedLog[0];
            String callUrl=splitedLog[1];
            String browserType=splitedLog[2];
            String calledDateTime=splitedLog[3];

            valueOfMap(codeMap,httpStatusCode);

            callUrl=StringUtils.removeStart(callUrl, FIXED_PREFIX_URL);
            String sub=StringUtils.substringBetween(callUrl,"apikey=","&");

            if(callUrl.contains("?")) {
                String[] split = StringUtils.split(callUrl, "?");
                String serviceID=split[0];
                urlMap.put(serviceID, urlMap.getOrDefault(serviceID, 0) + 1);
            }

            valueOfMap(keyMap,sub);

            valueOfMap(webMap,browserType);

            valueOfMap(timeMap,calledDateTime);
        }
    }
    public void valueOfMap(Map<String,Integer> map , String put){
        map.put(put,map.getOrDefault(put,0)+1);
    }
}
