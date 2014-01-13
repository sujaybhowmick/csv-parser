package com.optimus.csv.parser;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

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

        CSVParser parser = new CSVParserImpl(inputFile);

        Iterator<CSVRecord> records = parser.iterator();

        assertNotNull(records);

        assertTrue(records.hasNext());

        int i = 0;

        while(records.hasNext()){
            CSVRecord record = records.next();
            System.out.println(record.getRecordNumber());
            System.out.println(record.get("Details"));
            System.out.println(record.get("Month"));
            System.out.println(record.get("Amount"));
            assertEquals(i++, record.getRecordNumber());
        }

        List<CSVRecord> list = parser.getRecords();

        assertTrue(list.size() > 0);

        assertTrue(parser.size() == 4);
    }
}
