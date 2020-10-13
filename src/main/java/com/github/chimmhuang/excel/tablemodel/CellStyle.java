package com.github.chimmhuang.excel.tablemodel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.Serializable;

/**
 * @author Chimm Huang
 */
public class CellStyle implements Serializable {

    private static final long serialVersionUID = 6572274827392389515L;

    private BorderStyle borderTopEnum;
    private BorderStyle borderBottomEnum;
    private BorderStyle borderLeftEnum;
    private BorderStyle borderRightEnum;

    private short topBorderColor;
    private short bottomBorderColor;
    private short leftBorderColor;
    private short rightBorderColor;

    private short fillBackgroundColor;
    private short fillForegroundColor;

    private short dataFormat;
    private boolean hidden;
    private boolean locked;

    private HorizontalAlignment alignmentEnum;
    private VerticalAlignment verticalAlignmentEnum;

    private Font font;

    public CellStyle(XSSFCellStyle xssfCellStyle) {
        this.borderTopEnum = xssfCellStyle.getBorderTopEnum();
        this.borderBottomEnum = xssfCellStyle.getBorderBottomEnum();
        this.borderLeftEnum = xssfCellStyle.getBorderLeftEnum();
        this.borderRightEnum = xssfCellStyle.getBorderRightEnum();
        this.topBorderColor = xssfCellStyle.getTopBorderColor();
        this.bottomBorderColor = xssfCellStyle.getBottomBorderColor();
        this.leftBorderColor = xssfCellStyle.getLeftBorderColor();
        this.rightBorderColor = xssfCellStyle.getRightBorderColor();
        this.fillBackgroundColor = xssfCellStyle.getFillBackgroundColor();
        this.fillForegroundColor = xssfCellStyle.getFillForegroundColor();
        this.dataFormat = xssfCellStyle.getDataFormat();
        this.hidden = xssfCellStyle.getHidden();
        this.locked = xssfCellStyle.getLocked();
        this.alignmentEnum = xssfCellStyle.getAlignmentEnum();
        this.verticalAlignmentEnum = xssfCellStyle.getVerticalAlignmentEnum();
        this.font = new Font(xssfCellStyle.getFont());
    }

    public BorderStyle getBorderTopEnum() {
        return borderTopEnum;
    }

    public void setBorderTopEnum(BorderStyle borderTopEnum) {
        this.borderTopEnum = borderTopEnum;
    }

    public BorderStyle getBorderBottomEnum() {
        return borderBottomEnum;
    }

    public void setBorderBottomEnum(BorderStyle borderBottomEnum) {
        this.borderBottomEnum = borderBottomEnum;
    }

    public BorderStyle getBorderLeftEnum() {
        return borderLeftEnum;
    }

    public void setBorderLeftEnum(BorderStyle borderLeftEnum) {
        this.borderLeftEnum = borderLeftEnum;
    }

    public BorderStyle getBorderRightEnum() {
        return borderRightEnum;
    }

    public void setBorderRightEnum(BorderStyle borderRightEnum) {
        this.borderRightEnum = borderRightEnum;
    }

    public short getTopBorderColor() {
        return topBorderColor;
    }

    public void setTopBorderColor(short topBorderColor) {
        this.topBorderColor = topBorderColor;
    }

    public short getBottomBorderColor() {
        return bottomBorderColor;
    }

    public void setBottomBorderColor(short bottomBorderColor) {
        this.bottomBorderColor = bottomBorderColor;
    }

    public short getLeftBorderColor() {
        return leftBorderColor;
    }

    public void setLeftBorderColor(short leftBorderColor) {
        this.leftBorderColor = leftBorderColor;
    }

    public short getRightBorderColor() {
        return rightBorderColor;
    }

    public void setRightBorderColor(short rightBorderColor) {
        this.rightBorderColor = rightBorderColor;
    }

    public short getFillBackgroundColor() {
        return fillBackgroundColor;
    }

    public void setFillBackgroundColor(short fillBackgroundColor) {
        this.fillBackgroundColor = fillBackgroundColor;
    }

    public short getFillForegroundColor() {
        return fillForegroundColor;
    }

    public void setFillForegroundColor(short fillForegroundColor) {
        this.fillForegroundColor = fillForegroundColor;
    }

    public short getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(short dataFormat) {
        this.dataFormat = dataFormat;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public HorizontalAlignment getAlignmentEnum() {
        return alignmentEnum;
    }

    public void setAlignmentEnum(HorizontalAlignment alignmentEnum) {
        this.alignmentEnum = alignmentEnum;
    }

    public VerticalAlignment getVerticalAlignmentEnum() {
        return verticalAlignmentEnum;
    }

    public void setVerticalAlignmentEnum(VerticalAlignment verticalAlignmentEnum) {
        this.verticalAlignmentEnum = verticalAlignmentEnum;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
