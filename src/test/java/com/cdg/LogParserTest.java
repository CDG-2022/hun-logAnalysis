package com.cdg;

import org.apache.commons.lang3.StringUtils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LogParserTest {
    private static final String FIXED_PREFIX_URL ="http://apis.daum.net/search/";
    @Test
    public void parse(){
        String log= "[200][http://apis.daum.net/search/knowledge?apikey=e3ea&q=daum][IE][2009-06-10 08:00:03]";

        String[] splitedLog = parseLog(log);
        assertEquals(splitedLog[0],"200");
        assertEquals(splitedLog[1],"http://apis.daum.net/search/knowledge?apikey=e3ea&q=daum");
        assertEquals(splitedLog[2],"IE");
        assertEquals(splitedLog[3],"2009-06-10 08:00:03");

    }
    @Test
    public void parseUrl(){
        String urlLog="http://apis.daum.net/search/knowledge?apikey=e3ea&q=daum";
        assertEquals(parseUrl(urlLog),"e3ea");

    }
    @Test
    public void parseServiceId() {
        String parseLog="knowledge?apikey=e3ea&q=daum";
        assertEquals(parseServiceId(parseLog),"knowledge");
    }

    private String parseServiceId(String parseLog){

        String[] split = StringUtils.split(parseLog, "?");
        return split[0];
    }


    private String parseUrl(String urlLog) {
        urlLog =StringUtils.removeStart(urlLog, FIXED_PREFIX_URL);
        return StringUtils.substringBetween(urlLog,"apikey=","&");
    }


    private String[] parseLog(String log) {
        return StringUtils.substringsBetween(log, "[", "]");
    }


}