package com.github.chimmhuang.excel.tablemodel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.ReadingOrder;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

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

    private XSSFColor fillBackgroundXSSFColor;
    private XSSFColor fillForegroundXSSFColor;
    private FillPatternType fillPattern;

    private short dataFormat;
    private boolean hidden;
    private boolean locked;

    private short indention;
    private boolean wrapText;
    private boolean shrinkToFit;
    private ReadingOrder readingOrder;
    private boolean quotePrefixed;
    private short rotation;

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
        this.fillBackgroundXSSFColor = xssfCellStyle.getFillBackgroundXSSFColor();
        this.fillForegroundXSSFColor = xssfCellStyle.getFillForegroundXSSFColor();
        this.fillPattern = xssfCellStyle.getFillPattern();
        this.dataFormat = xssfCellStyle.getDataFormat();
        this.hidden = xssfCellStyle.getHidden();
        this.locked = xssfCellStyle.getLocked();
        this.indention = xssfCellStyle.getIndention();
        this.wrapText = xssfCellStyle.getWrapText();
        this.shrinkToFit = xssfCellStyle.getShrinkToFit();
        this.readingOrder = xssfCellStyle.getReadingOrder();
        this.quotePrefixed = xssfCellStyle.getQuotePrefixed();
        this.rotation = xssfCellStyle.getRotation();
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

    public XSSFColor getFillForegroundXSSFColor() {
        return fillForegroundXSSFColor;
    }

    public void setFillForegroundXSSFColor(XSSFColor fillForegroundXSSFColor) {
        this.fillForegroundXSSFColor = fillForegroundXSSFColor;
    }

    public XSSFColor getFillBackgroundXSSFColor() {
        return fillBackgroundXSSFColor;
    }

    public void setFillBackgroundXSSFColor(XSSFColor fillBackgroundXSSFColor) {
        this.fillBackgroundXSSFColor = fillBackgroundXSSFColor;
    }

    public FillPatternType getFillPattern() {
        return fillPattern;
    }

    public void setFillPattern(FillPatternType fillPattern) {
        this.fillPattern = fillPattern;
    }


    public short getIndention() {
        return indention;
    }

    public void setIndention(short indention) {
        this.indention = indention;
    }

    public boolean getWrapText() {
        return wrapText;
    }

    public void setWrapText(boolean wrapText) {
        this.wrapText = wrapText;
    }

    public boolean getShrinkToFit() {
        return shrinkToFit;
    }

    public void setShrinkToFit(boolean shrinkToFit) {
        this.shrinkToFit = shrinkToFit;
    }

    public ReadingOrder getReadingOrder() {
        return readingOrder;
    }

    public void setReadingOrder(ReadingOrder readingOrder) {
        this.readingOrder = readingOrder;
    }

    public boolean getQuotePrefixed() {
        return quotePrefixed;
    }

    public void setQuotePrefixed(boolean quotePrefixed) {
        this.quotePrefixed = quotePrefixed;
    }

    public short getRotation() {
        return rotation;
    }

    public void setRotation(short rotation) {
        this.rotation = rotation;
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
