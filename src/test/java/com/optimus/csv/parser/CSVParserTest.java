package com.optimus.csv.parser;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 12/31/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSVParserTest {


    @Test
    public void testParse() throws Exception{
        String inputFile = "src/test/resources/test.csv";

        CSVParser parser = new CSVParserImpl();

        Iterator<CSVRecord> records = parser.parse(inputFile);

        assertNotNull(records);

        assertTrue(records.hasNext());

        while(records.hasNext()){
            CSVRecord record = records.next();
            System.out.println(record.get("Details"));
            System.out.println(record.get("Month"));
            System.out.println(record.get("Amount"));
        }

    }
}
