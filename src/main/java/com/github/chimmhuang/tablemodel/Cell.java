package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.parser.ExcelHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;


/**
 * This class corresponds to each [cell] of excel.
 *
 * @author Chimm Huang
 */
public class Cell {

    private int row;
    private String col;
    private CellStyle cellStyle;
    private CellType cellType;
    private Object value;

    public Cell(XSSFCell xssfCell) {
        this.row = xssfCell.getRowIndex() + 1;
        this.col = ExcelHelper.getColName(xssfCell.getColumnIndex());
        this.cellStyle = new CellStyle(xssfCell.getCellStyle());
        this.cellType = xssfCell.getCellType();
        switch (cellType) {
            default:
            case _NONE:
            case STRING:
            case BLANK:
                this.value = xssfCell.getStringCellValue();
                break;
            case NUMERIC:
                this.value = xssfCell.getNumericCellValue();
                break;
            case BOOLEAN:
                this.value = xssfCell.getBooleanCellValue();
                break;
            case FORMULA:
                this.value = xssfCell.getCellFormula();
                break;
            case ERROR:
                this.value = xssfCell.getErrorCellValue();
                break;
        }
    }

    public void setCellFormula(String cellFormula) {
        this.value = cellFormula.replace("=", "");
        this.cellType = CellType.FORMULA;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
