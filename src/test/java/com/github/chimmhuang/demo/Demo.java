package com.github.chimmhuang.demo;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author Chimm Huang
 * @date 2020/9/22
 */
public class Demo {

    @Test
    public void test() throws Exception {
        File file = new File("src/main/resources/demo.xlsx");

        byte[] bytes = FileUtils.readFileToByteArray(file);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        XSSFWorkbook workbook = new XSSFWorkbook(byteArrayInputStream);

        XSSFSheet sheet = workbook.getSheetAt(1);

        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println(cell);

//                switch (cell.getCellType()) {
//                    case STRING:
//                        System.out.println("row:" + cell.getRowIndex() + " col:" + cell.getColumnIndex() + " value:" + cell.getStringCellValue());
//                        break;
//                    default:break;
//                }
            }

        }

//        for (XSSFTable table : sheet.getTables()) {
//            System.out.println(table);
//            for (CellReference allReferencedCell : table.getCellReferences().getAllReferencedCells()) {
//                System.out.println(allReferencedCell.getSheetName());
//            }
//        }

//        for (Entry<CellAddress, XSSFComment> entry : sheet.getCellComments().entrySet()) {
//            CellAddress cellAddress = entry.getKey();
//            XSSFComment xssfComment = entry.getValue();
//            System.out.println("row:" + cellAddress.getRow() + " col:" + cellAddress.getColumn() + " value: " + xssfComment.getString());
//        }


//        int lastRowNum = sheet.getLastRowNum();
//        for (int i = 0; i <= lastRowNum; i++) {
//            XSSFRow row = sheet.getRow(i);
//            System.out.println(row.getCell(0).getStringCellValue() + " -- 最后一列：" + row.getLastCellNum());
//        }

    }
}
