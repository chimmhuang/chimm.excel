package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.util.TableUtils;

import java.util.Map;

/**
 * @author Chimm Huang
 */
public class Row {

    /**
     * key - col-number. start from 1
     * value - Row{@link Row}
     */
    private final Map<Integer, Cell> colCellMap;

    public Row(Map<Integer, Cell> colCellMap) {
        this.colCellMap = colCellMap;
    }

    /**
     * get the cell of the row by cell index. cell index start from 1
     * @param cellNum cell index. start from 1
     */
    public Cell getCell(int cellNum) {
        return colCellMap.get(cellNum);
    }

    /**
     * get the cell of the row by cell name
     */
    public Cell getCell(String cellName) {
        Integer colIndex = TableUtils.getColIndex(cellName);
        return this.getCell(colIndex);
    }
}
