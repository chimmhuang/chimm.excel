package com.github.chimmhuang.demo;

import com.github.chimmhuang.antlr.ExcelVariableParserVisitor;
import com.github.chimmhuang.antlr.VariableParserLexer;
import com.github.chimmhuang.antlr.VariableParserParser;
import com.github.chimmhuang.antlr.VariableParserParser.ExprContext;
import com.github.chimmhuang.parser.TableDataHelper;
import com.github.chimmhuang.tablemodel.Cell;
import com.github.chimmhuang.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.tablemodel.InnerTable;
import com.github.chimmhuang.tablemodel.Row;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * @author Chimm Huang
 */
public class Demo {

    @Test
    public void test() throws Exception {
        File file = new File("src/test/resources/demo.xlsx");

        byte[] bytes = FileUtils.readFileToByteArray(file);

        ExcelWorkbook excelWorkbook = TableDataHelper.createWorkbook(bytes);

        InnerTable sheet = excelWorkbook.getSheet(0);

        Row row = sheet.getRow(2);

        Cell cell = row.getCell("N");

        String value = cell.getValue();
        System.out.println(value);

        // 词法解析
        VariableParserLexer lexer = new VariableParserLexer(CharStreams.fromString(value));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 语法解析
        VariableParserParser parser = new VariableParserParser(tokens);
        ExprContext tree = parser.expr();

        // 以 lisp 格式打印 AST
        System.out.println(tree.toString(parser));

        System.out.println(tree.getText());

        ExcelVariableParserVisitor visitor = new ExcelVariableParserVisitor();
        Object visit = visitor.visit(tree);
        System.out.println(visit);
    }
}