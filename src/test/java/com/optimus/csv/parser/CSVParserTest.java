package com.optimus.csv.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 12/31/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSVParserTest {


    @Test
    public void testCSVParse() throws Exception{
        String inputFile = "src/test/resources/test.csv";

        InputStream is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);

        CSVGrammarLexer lexer = new CSVGrammarLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CSVGrammarParser parser = new CSVGrammarParser(tokens);

        ParseTree tree = parser.file();

        CSVParser visitor = new CSVParser();

        visitor.visit(tree);

        Iterator<CSVRecord> records = visitor.iterator();

        while(records.hasNext()){
            CSVRecord record = records.next();
            System.out.println(record.get("Details"));
            System.out.println(record.get("Month"));
            System.out.println(record.get("Amount"));
        }

    }
}
