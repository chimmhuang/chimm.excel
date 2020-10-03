package com.github.chimmhuang.tablemodel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * this class corresponds to the entire excel [workbook].
 *
 * @author Chimm Huang
 */
public class ExcelWorkbook {

    private final XSSFWorkbook xssfWorkbook;

    public ExcelWorkbook(XSSFWorkbook xssfWorkbook) {
        this.xssfWorkbook = xssfWorkbook;
    }

    /**
     * get the specified sheet by index
     * @param index sheet index in excel. start from 0
     */
    public InnerTable getSheet(int index) {
        XSSFSheet sheetAt = xssfWorkbook.getSheetAt(index);
        return new InnerTable(sheetAt);
    }

    /**
     * get the specified sheet by sheet name
     * @param sheetName sheet name
     */
    public InnerTable getSheet(String sheetName) {
        XSSFSheet sheet = xssfWorkbook.getSheet(sheetName);
        return new InnerTable(sheet);
    }

    /**
     * get the apache-poi object and perform custom operations
     */
    public XSSFWorkbook getXssfWorkbook() {
        return xssfWorkbook;
    }
}
