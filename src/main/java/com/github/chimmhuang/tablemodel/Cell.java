package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.parser.ExcelHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.Serializable;


/**
 * This class corresponds to each [cell] of excel.
 *
 * @author Chimm Huang
 */
public class Cell implements Serializable {

    private static final long serialVersionUID = -2425553436158748501L;

    private int row;
    private String col;
    private CellStyle cellStyle;
    private CellType cellType;
    private MergedRegion mergedRegion;
    private Object value;

    public Cell(XSSFCell xssfCell) {
        this(xssfCell,null);
    }

    public Cell(XSSFCell xssfCell, CellRangeAddress cellAddresses) {
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

        if (cellAddresses != null) {
            int firstRow = cellAddresses.getFirstRow();
            int lastRow = cellAddresses.getLastRow();
            int firstColumn = cellAddresses.getFirstColumn();
            int lastColumn = cellAddresses.getLastColumn();

            this.mergedRegion = new MergedRegion(firstRow + 1, lastRow + 1, ExcelHelper.getColName(firstColumn), ExcelHelper.getColName(lastColumn));
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

    public MergedRegion getMergedRegion() {
        return mergedRegion;
    }

    public void setMergedRegion(MergedRegion mergedRegion) {
        this.mergedRegion = mergedRegion;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
        switch (value.getClass().getName()) {
            case "java.lang.Double":
            case "java.math.BigDecimal":
            case "java.util.Date":
            case "java.util.Calendar":
            case "java.time.LocalDate":
            case "java.time.LocalDateTime":
                cellType = CellType.NUMERIC;
                break;
            case "java.lang.String":
            case "org.apache.poi.ss.usermodel.RichTextString":
                cellType = CellType.STRING;
                break;
            case "java.lang.Boolean":
                cellType = CellType.BOOLEAN;
                break;
            default:break;
        }
    }
}
