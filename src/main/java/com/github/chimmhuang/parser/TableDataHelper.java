package com.github.chimmhuang.parser;

import com.github.chimmhuang.tablemodel.Cell;
import com.github.chimmhuang.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.tablemodel.InnerTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Chimm Huang
 */
public class TableDataHelper {

    /**
     * create excel workbook
     * @param bytes excel binary file
     */
    public static ExcelWorkbook createWorkbook(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new ExcelWorkbook(xssfWorkbook);
    }

    /**
     * get inner table
     * @param bytes excel binary file
     * @param sheetIndex sheet index
     */
    public static InnerTable getSheetInnerTable(byte[] bytes , int sheetIndex) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new InnerTable(xssfWorkbook.getSheetAt(sheetIndex));
    }

    /**
     * get inner table
     * @param bytes excel binary file
     * @param sheetName sheet name
     */
    public static InnerTable getSheetInnerTable(byte[] bytes , String sheetName) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new InnerTable(xssfWorkbook.getSheet(sheetName));
    }

    /**
     * fill variables into excel table
     * @param table excel sheet table
     * @param data params
     */
    public static void fillInData(InnerTable table, Object data) {
        for (Cell cell : table) {
            
            System.out.println(cell.getValue());
        }
    }
}
