package com.github.chimmhuang.excel.demo;

import com.github.chimmhuang.excel.ExcelHelper;
import com.github.chimmhuang.excel.school.Score;
import com.github.chimmhuang.excel.tablemodel.BorderPositionEnum;
import com.github.chimmhuang.excel.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.excel.tablemodel.MergedRegion;
import com.github.chimmhuang.excel.tablemodel.Row;
import com.github.chimmhuang.excel.tablemodel.SheetTable;
import com.github.chimmhuang.excel.school.ClassScore;
import com.github.chimmhuang.excel.school.GradesRanking;
import com.github.chimmhuang.excel.school.SchoolReportData;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author Chimm Huang
 */
public class Demo {

    @Test
    public void testFillInTable() throws Exception {

        // 获取 excel 二进制文件
        File file = new File("src/test/resources/demo.xlsx");
        byte[] bytes = FileUtils.readFileToByteArray(file);

        // 创建 table 对象
        ExcelWorkbook excelWorkbook = ExcelHelper.createWorkbook(bytes);
        SheetTable table = excelWorkbook.getSheet(0);

        // 定义 excel 表格数据
        SchoolReportData tableData = new SchoolReportData();

        /*
            ====================================
                       开始组装表格数据
            ====================================
         */
        tableData.setTitle("xx中学成绩单");

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
                new ClassScore(1,"高一1班", "李华", BigDecimal.valueOf(285), BigDecimal.valueOf(90), BigDecimal.valueOf(95), BigDecimal.valueOf(100)),
                new ClassScore(1,"高一1班", "小明", BigDecimal.valueOf(270), BigDecimal.valueOf(80), BigDecimal.valueOf(100), BigDecimal.valueOf(90)),
                new ClassScore(1,"高一1班", "张三", BigDecimal.valueOf(265), BigDecimal.valueOf(80), BigDecimal.valueOf(95), BigDecimal.valueOf(90)),
                new ClassScore(2,"高一2班", "小红", BigDecimal.valueOf(260), BigDecimal.valueOf(95), BigDecimal.valueOf(70), BigDecimal.valueOf(95)),
                new ClassScore(2,"高一2班", "马小跳", BigDecimal.valueOf(200), BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(80)),
                new ClassScore(2,"高一2班", "李三光", BigDecimal.valueOf(255), BigDecimal.valueOf(55), BigDecimal.valueOf(100), BigDecimal.valueOf(100)),
                new ClassScore(2,"高一2班", "李四", BigDecimal.valueOf(250), BigDecimal.valueOf(80), BigDecimal.valueOf(90), BigDecimal.valueOf(80)));
        tableData.setClassScoreList(classScoreList);

        tableData.setPrincipalComment("你们都是祖国未来的希望！");

        /*
            ====================================
                       表格数据组装完毕
                       -------------
                       开始动态设置表格
            ====================================
         */

        Row row13 = table.getRow(13);
        Row row14 = table.getRow(14);
        // 将 rowNum 大于 13 的都删除，进行动态表格添加
        table.removeRowGE(13);

        // 将班级成绩单列表按照 班级id 进行分组遍历
        int classScoreIndex = 0;
        Map<Integer, List<ClassScore>> classScoreMap = tableData.getClassScoreList().stream()
                .collect(Collectors.groupingBy(ClassScore::getClassId));
        for (Entry<Integer, List<ClassScore>> entry : classScoreMap.entrySet()) {
            Integer classId = entry.getKey();
            for (ClassScore classScore : entry.getValue()) {
                // 复制模板的第13行
                Row copy = row13.copy();
                // 此处直接设置值也行。设置值或者设置变量，建议统一。我在此处设置为变量
                copy.getCell("A").setValue("${classScoreList["+classScoreIndex+"].className}");
                copy.getCell("B").setValue("${classScoreList["+classScoreIndex+"].name}");
                copy.getCell("D").setValue("${classScoreList["+classScoreIndex+"].chineseScore}");
                copy.getCell("E").setValue("${classScoreList["+classScoreIndex+"].mathScore}");
                copy.getCell("F").setValue("${classScoreList["+classScoreIndex+"].englishScore}");

                classScoreIndex++;

                // 设置完毕后，添加进表格
                table.appendRow(copy);
            }
        }

        // 合并单元格，【此处仅展示功能，需要合并的 rowNum 建议动态计算】
        table.mergeCell(13, 15, "A", "A");
        table.mergeCell(16, 19, "A", "A");
//        MergedRegion mergedRegion1 = new MergedRegion(13, 15, "A", "A");
//        MergedRegion mergedRegion2 = new MergedRegion(16, 19, "A", "A");
//        table.mergeCellBatch(Arrays.asList(mergedRegion1, mergedRegion2));

        // 设置指定行的单元格样式，【此处仅展示功能：加粗，rowNum 建议动态计算】
        table.getRow(19).setBorderStyle(BorderPositionEnum.BOTTOM, BorderStyle.MEDIUM);

        // 班级成绩设置好之后，将最后的校长评语添加进表格
        table.appendRow(row14);

        // 将变量的值填充进表格
        ExcelHelper.fillInData(table, tableData);


        // 获取转换后的二进制
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