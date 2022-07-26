package com.cdg;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
public class LogParser {
    private static String startUrl="http://apis.daum.net/search/";

    private final Map<String,Integer> keyMap= new HashMap<>();
    private final Map<String,Integer> codeMap= new HashMap<>();
    private final Map<String,Integer> urlMap= new HashMap<>();
    private final Map<String,Integer> webMap= new HashMap<>();
    private final Map<String,Integer> timeMap= new HashMap<>();


    public void parse(BufferedReader reader) throws IOException {
        while (true){
            String[] urlLog = StringUtils.substringsBetween(reader.readLine(), "[", "]");
            if (urlLog==null) break;
            String state=urlLog[0];
            String url=urlLog[1];
            String browser=urlLog[2];
            String callTime=urlLog[3];

            mapPutter(codeMap,state);

            url=StringUtils.removeStart(url,startUrl);
            String sub=StringUtils.substringBetween(url,"apikey=","q");

            if(url.contains("?")) {
                String[] split = StringUtils.split(url, "?");
                String serviceID=split[0];
                urlMap.put(serviceID, urlMap.getOrDefault(serviceID, 0) + 1);
            }

            mapPutter(keyMap,sub);

            mapPutter(webMap,browser);

            mapPutter(timeMap,callTime);
        }
    }
    public void mapPutter(Map<String,Integer> map ,String put){
        map.put(put,map.getOrDefault(put,0)+1);
    }
}
