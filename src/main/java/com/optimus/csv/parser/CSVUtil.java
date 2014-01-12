package com.optimus.csv.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 1/10/14
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSVUtil {

    public static Iterator<CSVRecord> parse(InputStream is)
            throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(is);

        CSVGrammarLexer lexer = new CSVGrammarLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CSVGrammarParser parser = new CSVGrammarParser(tokens);

        ParseTree tree = parser.file();

        CSVGrammarVisitorImpl visitor = new CSVGrammarVisitorImpl();

        visitor.visit(tree);

        return visitor.iterator();

    }

    public static Iterator<CSVRecord> parse(String fileName)
            throws Exception {

        InputStream is = new FileInputStream(new File(fileName));
        return parse(is);
    }

    public static Iterator<CSVRecord> parse(File file)
            throws Exception {
        InputStream is = new FileInputStream(file);
        return parse(is);
    }
}
