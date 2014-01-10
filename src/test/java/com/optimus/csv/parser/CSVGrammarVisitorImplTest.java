package com.optimus.csv.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 12/31/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSVGrammarVisitorImplTest {


    @Test
    public void testVisitRow() throws Exception{
        String inputFile = "src/test/resources/test.csv";

        InputStream is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);

        CSVGrammarLexer lexer = new CSVGrammarLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CSVGrammarParser parser = new CSVGrammarParser(tokens);

        ParseTree tree = parser.file();

        CSVGrammarVisitorImpl visitor = new CSVGrammarVisitorImpl();

        visitor.visit(tree);
    }
}
