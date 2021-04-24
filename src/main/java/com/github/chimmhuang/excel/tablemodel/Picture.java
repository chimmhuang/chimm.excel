package com.github.chimmhuang.excel.tablemodel;

import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;

import java.io.Serializable;

/**
 * @author Chimm Huang
 */
public class Picture implements Serializable {

    private static final long serialVersionUID = -2649347083856941587L;

    private final byte[] pictureByte;
    private final PictureType type;

    /**
     * horizontal pixels
     */
    private int pixelX;
    /**
     * vertical pixels
     */
    private int pixelY;

    /**
     * see {@link AnchorType}
     */
    private AnchorType anchortype;

    /**
         see {@link org.apache.poi.xssf.usermodel.XSSFClientAnchor#XSSFClientAnchor(int, int, int, int, int, int, int, int)}
         dx1 – the x coordinate within the first cell.
         dy1 – the y coordinate within the first cell.
         dx2 – the x coordinate within the second cell.
         dy2 – the y coordinate within the second cell.
     */
    private int dx1 = 0;
    private int dy1 = 0;
    private int dx2 = 0;
    private int dy2 = 0;

    /**
     * start from 1.
     */
    private int firstRowNum;
    private int lastRowNum;

    /**
     * start from "A"
     */
    private String firstColName;
    private String lastColName;

    public Picture(byte[] pictureByte, PictureType type, int pixelX, int pixelY, int firstRowNum, int lastRowNum, String firstColName, String lastColName) {
        this.pictureByte = pictureByte;
        this.type = type;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        this.firstRowNum = firstRowNum;
        this.lastRowNum = lastRowNum;
        this.firstColName = firstColName;
        this.lastColName = lastColName;
    }

    public Picture(byte[] pictureByte, PictureType type, int pixelX, int pixelY, int firstRowNum, int lastRowNum, String firstColName, String lastColName, int dx1, int dy1, int dx2, int dy2) {
        this(pictureByte, type, pixelX, pixelY, firstRowNum, lastRowNum, firstColName, lastColName);
        this.dx1 = dx1;
        this.dy1 = dy1;
        this.dx2 = dx2;
        this.dy2 = dy2;
    }

    public Picture(byte[] pictureByte, PictureType type, int pixelX, int pixelY, int firstRowNum, int lastRowNum, String firstColName, String lastColName, AnchorType anchortype) {
        this(pictureByte, type, pixelX, pixelY, firstRowNum, lastRowNum, firstColName, lastColName);
        this.anchortype = anchortype;
    }

    public Picture(byte[] pictureByte, PictureType type, int pixelX, int pixelY, int firstRowNum, int lastRowNum, String firstColName, String lastColName, int dx1, int dy1, int dx2, int dy2, AnchorType anchortype) {
        this(pictureByte, type, pixelX, pixelY, firstRowNum, lastRowNum, firstColName, lastColName, dx1, dy1, dx2, dy2);
        this.anchortype = anchortype;
    }

    public int getDx1() {
        return dx1;
    }

    public void setDx1(int dx1) {
        this.dx1 = dx1;
    }

    public int getDy1() {
        return dy1;
    }

    public void setDy1(int dy1) {
        this.dy1 = dy1;
    }

    public int getDx2() {
        return dx2;
    }

    public void setDx2(int dx2) {
        this.dx2 = dx2;
    }

    public int getDy2() {
        return dy2;
    }

    public void setDy2(int dy2) {
        this.dy2 = dy2;
    }

    public int getFirstRowNum() {
        return firstRowNum;
    }

    public void setFirstRowNum(int firstRowNum) {
        this.firstRowNum = firstRowNum;
    }

    public int getLastRowNum() {
        return lastRowNum;
    }

    public void setLastRowNum(int lastRowNum) {
        this.lastRowNum = lastRowNum;
    }

    public String getFirstColName() {
        return firstColName;
    }

    public void setFirstColName(String firstColName) {
        this.firstColName = firstColName;
    }

    public String getLastColName() {
        return lastColName;
    }

    public void setLastColName(String lastColName) {
        this.lastColName = lastColName;
    }

    public byte[] getPictureByte() {
        return pictureByte;
    }

    public PictureType getType() {
        return type;
    }

    public AnchorType getAnchortype() {
        return anchortype;
    }

    public void setAnchortype(AnchorType anchortype) {
        this.anchortype = anchortype;
    }

    public int getPixelX() {
        return pixelX;
    }

    public void setPixelX(int pixelX) {
        this.pixelX = pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public void setPixelY(int pixelY) {
        this.pixelY = pixelY;
    }
}
