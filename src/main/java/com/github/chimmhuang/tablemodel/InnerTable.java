package com.github.chimmhuang.tablemodel;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chimm Huang
 */
public class InnerTable {

    /**
     * key - row-number. start from 1
     * value - Row{@link Row}
     */
    private final Map<Integer, Row> rowMap = new ConcurrentHashMap<>();

    public InnerTable(XSSFSheet xssfSheet) {
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = xssfSheet.rowIterator();
        rowIterator.forEachRemaining(row -> {
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
}
