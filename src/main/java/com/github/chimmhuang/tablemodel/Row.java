package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.parser.ExcelHelper;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * this class corresponds to each [row] of Excel.
 *
 * @author Chimm Huang
 */
public class Row implements Iterable<Cell>, Serializable {

    private static final long serialVersionUID = 5924803090073879378L;

    /**
     * row-num. start from 1
     */
    private int rowNum;
    private boolean zeroHeight;
    private float heightInPoints;
    private short height;

    /**
     * key - col-name.
     * value - Row{@link Row}
     */
    private final Map<String, Cell> colCellMap;

    public Row(org.apache.poi.ss.usermodel.Row row, Map<String, Cell> colCellMap) {
        rowNum = row.getRowNum() + 1;
        zeroHeight = row.getZeroHeight();
        heightInPoints = row.getHeightInPoints();
        height = row.getHeight();
        this.colCellMap = colCellMap;
    }

    /**
     * get the cell of the row by cell index. cell index start from 0
     *
     * @param cellIndex cell index. start from 0
     */
    public Cell getCell(int cellIndex) {
        return colCellMap.get(ExcelHelper.getColName(cellIndex));
    }

    /**
     * get the cell of the row by cell name
     */
    public Cell getCell(String cellName) {
        return colCellMap.get(cellName);
    }


    /**
     * deep clone
     * @return new Row
     */
    public Row copy() {
        return SerializationUtils.clone(this);
    }

    /**
     * traverse all cells in the row
     */
    @Override
    public Iterator<Cell> iterator() {
        return colCellMap.values().iterator();
    }

    public boolean getZeroHeight() {
        return zeroHeight;
    }

    public float getHeightInPoints() {
        return heightInPoints;
    }

    public short getHeight() {
        return height;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
}
