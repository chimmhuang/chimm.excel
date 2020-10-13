package com.github.chimmhuang.excel.tablemodel;

import org.apache.poi.ss.usermodel.FontScheme;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.Serializable;

/**
 * @author Chimm Huang
 */
public class Font implements Serializable {

    private static final long serialVersionUID = 6517940000596931192L;

    private boolean bold;
    private int charSet;
    private short color;
    private int family;
    private short fontHeight;
    private short fontHeightInPoints;
    private String fontName;
    private boolean italic;
    private FontScheme scheme;
    private boolean strikeout;
    private short themeColor;
    private short typeOffset;
    private byte underline;

    public Font(XSSFFont xssfFont) {
        this.bold = xssfFont.getBold();
        this.charSet = xssfFont.getCharSet();
        this.color = xssfFont.getColor();
        this.family = xssfFont.getFamily();
        this.fontHeight = xssfFont.getFontHeight();
        this.fontHeightInPoints = xssfFont.getFontHeightInPoints();
        this.fontName = xssfFont.getFontName();
        this.italic = xssfFont.getItalic();
        this.scheme = xssfFont.getScheme();
        this.strikeout = xssfFont.getStrikeout();
        this.themeColor = xssfFont.getThemeColor();
        this.typeOffset = xssfFont.getTypeOffset();
        this.underline = xssfFont.getUnderline();
    }

    public boolean getBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public int getCharSet() {
        return charSet;
    }

    public void setCharSet(int charSet) {
        this.charSet = charSet;
    }

    public short getColor() {
        return color;
    }

    public void setColor(short color) {
        this.color = color;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public short getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(short fontHeight) {
        this.fontHeight = fontHeight;
    }

    public short getFontHeightInPoints() {
        return fontHeightInPoints;
    }

    public void setFontHeightInPoints(short fontHeightInPoints) {
        this.fontHeightInPoints = fontHeightInPoints;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public boolean getItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public FontScheme getScheme() {
        return scheme;
    }

    public void setScheme(FontScheme scheme) {
        this.scheme = scheme;
    }

    public boolean getStrikeout() {
        return strikeout;
    }

    public void setStrikeout(boolean strikeout) {
        this.strikeout = strikeout;
    }

    public short getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(short themeColor) {
        this.themeColor = themeColor;
    }

    public short getTypeOffset() {
        return typeOffset;
    }

    public void setTypeOffset(short typeOffset) {
        this.typeOffset = typeOffset;
    }

    public byte getUnderline() {
        return underline;
    }

    public void setUnderline(byte underline) {
        this.underline = underline;
    }
}
