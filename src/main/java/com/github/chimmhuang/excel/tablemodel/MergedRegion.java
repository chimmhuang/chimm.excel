package com.github.chimmhuang.excel.tablemodel;

import java.io.Serializable;

/**
 * @author Chimm Huang
 */
public class MergedRegion implements Serializable {

    private static final long serialVersionUID = 5277762596563003264L;

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

    public MergedRegion() {
    }

    public MergedRegion(int firstRowNum, int lastRowNum, String firstColName, String lastColName) {
        this.firstRowNum = firstRowNum;
        this.lastRowNum = lastRowNum;
        this.firstColName = firstColName;
        this.lastColName = lastColName;
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
}
