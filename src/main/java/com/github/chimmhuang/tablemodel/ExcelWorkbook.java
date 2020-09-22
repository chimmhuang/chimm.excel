package com.github.chimmhuang.tablemodel;

import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author Chimm Huang
 */
public class ExcelWorkbook {

    private InnerTable innerTable;
    private XSSFWorkbook xssfWorkbook;

    public ExcelWorkbook(byte[] buf) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        this.xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
    }

    public InnerTable getSheet(int index) {
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(index);
        Map<CellAddress, XSSFComment> cellComments = sheetAt.getCellComments();

        return null;
    }
}
