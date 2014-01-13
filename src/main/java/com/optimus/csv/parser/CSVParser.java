package com.optimus.csv.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 1/12/14
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CSVParser extends Iterable<CSVRecord> {

    Iterator<CSVRecord> parse(final String fileName) throws IOException;

    Iterator<CSVRecord> parse(final InputStream is) throws IOException;

    List<CSVRecord> getRecords();

    int size();
}
