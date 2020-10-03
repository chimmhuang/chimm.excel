package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.util.TableUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * this class corresponds to each [row] of Excel.
 *
 * @author Chimm Huang
 */
public class Row implements Iterable<Cell> {

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

    /**
     * traverse all cells in the row
     */
    @Override
    public Iterator<Cell> iterator() {
        return colCellMap.values().iterator();
    }
}
