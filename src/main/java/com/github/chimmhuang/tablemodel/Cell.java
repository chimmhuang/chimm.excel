package com.github.chimmhuang.tablemodel;

/**
 * @author Chimm Huang
 */
public class Cell {

    private final org.apache.poi.ss.usermodel.Cell cell;

    public Cell(org.apache.poi.ss.usermodel.Cell cell) {
        this.cell = cell;
    }

    public String getValue() {
        return cell.getStringCellValue();
    }
}
