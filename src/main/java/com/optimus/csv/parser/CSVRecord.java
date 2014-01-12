package com.optimus.csv.parser;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 1/12/14
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public final class CSVRecord implements Serializable, Iterable<String> {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private final Map<String, Integer> mapping;

    private final String[] values;

    private final long recordNumber;


    CSVRecord(final String[] values, final Map<String, Integer> mapping,
                                                    final long recordNumber){
        this.mapping = mapping;
        this.values = values;
        this.recordNumber = recordNumber;
    }

    @Override
    public Iterator<String> iterator() {
        return Arrays.asList(values).iterator();
    }

    public String get(final int index){
        return values[index];
    }

    public String get(final String name){
        if(this.mapping == null){
            throw new IllegalStateException(
                    "No header mapping sepcified, " +
                            "the record values can't be accessed");
        }

        final Integer index = this.mapping.get(name);

        if(index == null){
            throw new IllegalArgumentException(String.format(
                    "Mapping for %s not found, expected one of %s", name,
                    this.mapping.keySet()));
        }
        try {
            return this.values[index];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException(String.format(
                    "Index for header '%s' is %d but CSVRecord only has %d values!",
                    name, index, Integer.valueOf(this.values.length)));
        }
    }

    public long getRecordNumber(){
        return this.recordNumber;
    }

    public int size(){
        return values.length;
    }
}
