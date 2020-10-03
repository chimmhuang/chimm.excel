package com.github.chimmhuang.tablemodel;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class corresponds to the [sheet page] of Excel.
 *
 * @author Chimm Huang
 */
public class InnerTable implements Iterable<Cell> {

    /**
     * key - row-number. start from 1
     * value - Row{@link Row}
     */
    private final Map<Integer, Row> rowMap = new ConcurrentHashMap<>();

    /**
     * the last row num in excel.
     * when the object is instantiated, the value of the variable will be updated.
     */
    private int lastRowNum = 0;

    public InnerTable(XSSFSheet xssfSheet) {
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = xssfSheet.rowIterator();
        rowIterator.forEachRemaining(row -> {
            lastRowNum = Math.max(lastRowNum, row.getRowNum());
            Map<Integer, Cell> colCellMap = new ConcurrentHashMap<>();
            Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
            cellIterator.forEachRemaining(cell -> colCellMap.put(cell.getColumnIndex() + 1, new Cell(cell)));
            rowMap.put(row.getRowNum() + 1, new Row(colCellMap));
        });
    }

    /**
     * get the specified row by row-number
     */
    public Row getRow(int rowNum) {
        return rowMap.get(rowNum);
    }

    public int getLastRowNum() {
        return lastRowNum;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellRowIterator();
    }

    public class CellRowIterator implements Iterator<Cell> {
        private int currentRowNum = 1;
        private Iterator<Cell> currentCellIterator;


        /**
         * traverse every valid cell.
         * if there is a valid cell, then return true, after traversing the last cell, return false.
         */
        @Override
        public boolean hasNext() {
            if (currentCellIterator == null) {
                if (currentRowNum <= getLastRowNum()) {
                    Row currentRow = getRow(currentRowNum);
                    // init cell iterator.
                    currentCellIterator = currentRow.iterator();
                } else {
                    return false;
                }
            }

            /*
                traverse all cells, line by line,
                if the current line has been traversed, then traverse the cells of the next line.
             */
            if (!currentCellIterator.hasNext()) {
                currentRowNum++;
                // get the cell of the next row.
                if (currentRowNum <= getLastRowNum()) {
                    Row currentRow = getRow(currentRowNum);
                    currentCellIterator = currentRow.iterator();
                } else {
                    return false;
                }
            }

            // start traversing current cells.
            return currentCellIterator.hasNext();
        }

        @Override
        public Cell next() {
            if (hasNext()) {
                return currentCellIterator.next();
            }
            return null;
        }
    }
}
