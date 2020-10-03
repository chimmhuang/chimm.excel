package com.github.chimmhuang.demo;

import com.github.chimmhuang.antlr.ExcelVariableParserVisitor;
import com.github.chimmhuang.antlr.VariableParserLexer;
import com.github.chimmhuang.antlr.VariableParserParser;
import com.github.chimmhuang.antlr.VariableParserParser.ExprContext;
import com.github.chimmhuang.parser.TableDataHelper;
import com.github.chimmhuang.school.ClassScore;
import com.github.chimmhuang.school.GradesRanking;
import com.github.chimmhuang.school.SchoolReportData;
import com.github.chimmhuang.school.Score;
import com.github.chimmhuang.tablemodel.Cell;
import com.github.chimmhuang.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.tablemodel.InnerTable;
import com.github.chimmhuang.tablemodel.Row;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Row row = sheet.getRow(1);

        Cell cell = row.getCell("A");

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

    @Test
    public void testFillInTable() throws Exception {
        File file = new File("src/test/resources/demo.xlsx");

        byte[] bytes = FileUtils.readFileToByteArray(file);

        ExcelWorkbook excelWorkbook = TableDataHelper.createWorkbook(bytes);

        InnerTable sheet = excelWorkbook.getSheet(0);

        // define table data
        SchoolReportData tableData = new SchoolReportData();

        GradesRanking gradesRanking = new GradesRanking();
        Score top1 = new Score("李华", BigDecimal.valueOf(285), BigDecimal.valueOf(90), BigDecimal.valueOf(95), BigDecimal.valueOf(100));
        Score top2 = new Score("小明", BigDecimal.valueOf(270), BigDecimal.valueOf(80), BigDecimal.valueOf(100), BigDecimal.valueOf(90));
        Score top3 = new Score("小红",BigDecimal.valueOf(260),BigDecimal.valueOf(95),BigDecimal.valueOf(70),BigDecimal.valueOf(95));
        gradesRanking.setTop1(top1);
        gradesRanking.setTop2(top2);
        gradesRanking.setTop3(top3);
        tableData.setGradesRanking(gradesRanking);

        List<String> hotCommentsList = Arrays.asList(
                "我们都应该向成绩好的同学们学习！加油！",
                "学好数理化，走遍天下都不怕",
                "数学不学好，一切皆为0");
        tableData.setHotCommentsList(hotCommentsList);


        List<ClassScore> classScoreList = Arrays.asList(
                new ClassScore("高一1班", "李华", BigDecimal.valueOf(285), BigDecimal.valueOf(90), BigDecimal.valueOf(95), BigDecimal.valueOf(100)),
                new ClassScore("高一1班", "小明", BigDecimal.valueOf(270), BigDecimal.valueOf(80), BigDecimal.valueOf(100), BigDecimal.valueOf(90)),
                new ClassScore("高一1班", "张三", BigDecimal.valueOf(265), BigDecimal.valueOf(80), BigDecimal.valueOf(95), BigDecimal.valueOf(90)),
                new ClassScore("高一2班", "小红", BigDecimal.valueOf(260), BigDecimal.valueOf(95), BigDecimal.valueOf(70), BigDecimal.valueOf(95)),
                new ClassScore("高一2班", "马小跳", BigDecimal.valueOf(200), BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(80)),
                new ClassScore("高一2班", "李三光", BigDecimal.valueOf(255), BigDecimal.valueOf(55), BigDecimal.valueOf(100), BigDecimal.valueOf(100)),
                new ClassScore("高一2班", "李四", BigDecimal.valueOf(250), BigDecimal.valueOf(80), BigDecimal.valueOf(90), BigDecimal.valueOf(80)));
        tableData.setClassScoreList(classScoreList);

        tableData.setPrincipalComment("允德允能");

        TableDataHelper.fillInData(sheet, tableData);
    }
}