package com.github.chimmhuang.demo;

import com.github.chimmhuang.parser.ExcelHelper;
import com.github.chimmhuang.school.ClassScore;
import com.github.chimmhuang.school.GradesRanking;
import com.github.chimmhuang.school.SchoolReportData;
import com.github.chimmhuang.school.Score;
import com.github.chimmhuang.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.tablemodel.InnerTable;
import com.github.chimmhuang.tablemodel.Row;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chimm Huang
 */
public class Demo {

    @Test
    public void testFillInTable() throws Exception {
        File file = new File("src/test/resources/demo.xlsx");

        byte[] bytes = FileUtils.readFileToByteArray(file);

        ExcelWorkbook excelWorkbook = ExcelHelper.createWorkbook(bytes);
        InnerTable table = excelWorkbook.getSheet(0);

        // define table data
        SchoolReportData tableData = new SchoolReportData();

        tableData.setTitle("中和中学成绩单");

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

//        Row row13 = table.getRow(13);
//        Row row14 = table.getRow(14);
//
//
//        Row row15 = table.appendRow(row13);
//        row15.getCell("C").setValue("你好");
//
//        Row row16 = table.appendRow(row13);
//        row16.getCell("C").setValue("我不好");
//        table.appendRow(row13);
//        table.appendRow(row14);
//
//        table.removeRow(row13);
//        table.removeRow(14);

        ExcelHelper.fillInData(table, tableData);



        byte[] bytes1 = ExcelHelper.convert2Byte(table);

        // 获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xlsx";

        File targetFile = new File(filePath);
        FileOutputStream fos = new FileOutputStream(targetFile);
        fos.write(bytes1);

        fos.close();
    }
}