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
    private int firstRowRum;
    private int lastRowRum;

    /**
     * start from "A"
     */
    private String firstColName;
    private String lastColName;

    public MergedRegion() {
    }

    public MergedRegion(int firstRowRum, int lastRowRum, String firstColName, String lastColName) {
        this.firstRowRum = firstRowRum;
        this.lastRowRum = lastRowRum;
        this.firstColName = firstColName;
        this.lastColName = lastColName;
    }

    public int getFirstRowRum() {
        return firstRowRum;
    }

    public void setFirstRowRum(int firstRowRum) {
        this.firstRowRum = firstRowRum;
    }

    public int getLastRowRum() {
        return lastRowRum;
    }

    public void setLastRowRum(int lastRowRum) {
        this.lastRowRum = lastRowRum;
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
