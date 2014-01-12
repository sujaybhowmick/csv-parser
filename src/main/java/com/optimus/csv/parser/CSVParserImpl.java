package com.optimus.csv.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 12/31/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSVParserImpl extends CSVGrammarBaseVisitor<Void>
        implements CSVParser {

    private List<CSVRecord> records = new ArrayList<CSVRecord>();


    private Map<String, Integer> mapping;

    private int recordNumber = 0;

    @Override
    public Void visitEmpty(@NotNull CSVGrammarParser.EmptyContext ctx) {
        return super.visitEmpty(ctx);
    }

    @Override
    public Void visitRow(@NotNull CSVGrammarParser.RowContext ctx) {
        List<CSVGrammarParser.FieldContext> fieldContexts = ctx.field();
        List<String> values = new ArrayList<String>();

        for(CSVGrammarParser.FieldContext f: fieldContexts){
            values.add(f.getText());

        }
        this.records.add(new CSVRecord(values.toArray(new String[]{}),
                                mapping, recordNumber++));
        return null;
    }

    @Override
    public Void visitHdr(@NotNull CSVGrammarParser.HdrContext hdrCtx) {
        mapping = new LinkedHashMap<String, Integer>();
        List<CSVGrammarParser.FieldContext> fieldContexts = hdrCtx.row().field();
        Integer index = 0;
        for(CSVGrammarParser.FieldContext f: fieldContexts){
            mapping.put(f.getText(), index++);
        }
        return null;
    }

    @Override
    public Iterator<CSVRecord> iterator() {
        return records.iterator();
    }

    @Override
    public Iterator<CSVRecord> parse(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        return parse(is);
    }

    @Override
    public Iterator<CSVRecord> parse(final InputStream is) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(is);

        CSVGrammarLexer lexer = new CSVGrammarLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CSVGrammarParser parser = new CSVGrammarParser(tokens);

        ParseTree tree = parser.file();

        CSVParserImpl visitor = new CSVParserImpl();

        visitor.visit(tree);

        return visitor.iterator();
    }
}
