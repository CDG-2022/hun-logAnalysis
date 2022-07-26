package com.cdg;



public class App
{

    public static void main( String[] args ) throws Exception {
        LogParser logParser = new LogParser();
        LogFileReader reader = new LogFileReader(logParser);
        reader.read();
        Printer printer = new Printer(reader);
        printer.print();

    }
}