package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.util.TableUtils;

import java.util.Map;

/**
 * @author Chimm Huang
 */
public class Row {

    private final Map<Integer, Cell> colCellMap;

    public Row(Map<Integer, Cell> colCellMap) {
        this.colCellMap = colCellMap;
    }

    /**
     * get the cell of the row by cell index. cell index start from 0
     * @param cellNum cell index. start from 0
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
