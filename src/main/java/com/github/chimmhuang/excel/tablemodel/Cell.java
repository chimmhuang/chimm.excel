package com.github.chimmhuang.excel.tablemodel;

import com.github.chimmhuang.excel.ExcelHelper;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;

import java.io.Serializable;


/**
 * 该类对应于 Excel 的 [单元格]
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
    private String hyperlink;
    private HyperlinkType hyperlinkType;
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

        XSSFHyperlink xssfHyperlink = xssfCell.getHyperlink();
        if (xssfHyperlink != null) {
            this.hyperlink = xssfHyperlink.getAddress();
            this.hyperlinkType = xssfHyperlink.getType();
        }
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

    public String getHyperlink() {
        return hyperlink;
    }

    public HyperlinkType getHyperlinkType() {
        return hyperlinkType;
    }

    /**
     * 设置超链接
     * set hyper link
     *
     * @param hyperlink 链接地址
     * @param hyperlinkType 超链接类型
     * @param fontUnderline 下划线类型
     */
    public void setHyperlink(String hyperlink, HyperlinkType hyperlinkType, FontUnderline fontUnderline) {
        this.hyperlink = hyperlink;
        this.hyperlinkType = hyperlinkType;
        this.cellStyle.getFont().setUnderline(fontUnderline);
        this.cellStyle.getFont().setColor(IndexedColors.BLUE);
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

    /**
     * 设置值
     * set value
     */
    public void setValue(Object value) {
        this.value = value;
        if (value == null) {
            return;
        }

        switch (value.getClass().getName()) {
            case "java.lang.Integer":
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

    /**
     * 设置公式
     * set formula
     *
     * @param formula 公式
     */
    public void setFormula(String formula) {
        if (formula.startsWith("=")) {
            formula = formula.replaceFirst("=", "");
        }
        value = formula;
        cellType = CellType.FORMULA;
    }

    /**
     * 设置该单元格的边框的样式，你可以更改边框的样式，如粗线、虚线等
     * set border style of this cell, you can change the style of the border, such as thick line, dotted line, etc.
     *
     * @param positionEnum position enum
     * @param borderStyle style enum
     */
    public void setBorderStyle(BorderPositionEnum positionEnum, BorderStyle borderStyle) {
        switch (positionEnum) {
            default:
            case AROUND:
                cellStyle.setBorderTopEnum(borderStyle);
                cellStyle.setBorderBottomEnum(borderStyle);
                cellStyle.setBorderLeftEnum(borderStyle);
                cellStyle.setBorderRightEnum(borderStyle);
                break;
            case TOP:
                cellStyle.setBorderTopEnum(borderStyle);
                break;
            case BOTTOM:
                cellStyle.setBorderBottomEnum(borderStyle);
                break;
            case LEFT:
                cellStyle.setBorderLeftEnum(borderStyle);
                break;
            case RIGHT:
                cellStyle.setBorderRightEnum(borderStyle);
                break;
        }
    }

    /**
     * 设置超链接（URL）
     * set hyper link(url)
     *
     * @param url hyper link
     */
    public void setHyperlinkURL(String url) {
        this.setHyperlink(url, HyperlinkType.URL, FontUnderline.SINGLE);
    }
}
