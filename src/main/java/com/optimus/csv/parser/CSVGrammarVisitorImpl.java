package com.optimus.csv.parser;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sujay
 * Date: 12/31/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
class CSVGrammarVisitorImpl extends CSVGrammarBaseVisitor<Void> {

    private List<Map<String, String>> records =
                                        new ArrayList<Map<String, String>>();

    private List<String> header;

    @Override
    public Void visitEmpty(@NotNull CSVGrammarParser.EmptyContext ctx) {
        return super.visitEmpty(ctx);
    }

    @Override
    public Void visitRow(@NotNull CSVGrammarParser.RowContext ctx) {
        List<CSVGrammarParser.FieldContext> fieldContexts = ctx.field();
        int i = 0;
        Map<String, String> m = new LinkedHashMap<String, String>();
        for(CSVGrammarParser.FieldContext f: fieldContexts){
            m.put(header.get(i), f.getText());
            i++;
        }
        records.add(m);
        return null;
    }

    @Override
    public Void visitHdr(@NotNull CSVGrammarParser.HdrContext hdrCtx) {
        header = new ArrayList<String>();
        List<CSVGrammarParser.FieldContext> fieldContexts = hdrCtx.row().field();
        for(CSVGrammarParser.FieldContext f: fieldContexts){
            header.add(f.getText());
        }
        return null;
    }

    public List<Map<String, String>> getRecords(){
        return this.records;
    }
}
