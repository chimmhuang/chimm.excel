package com.github.chimmhuang.tablemodel;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chimm Huang
 */
public class InnerTable {

    private final Map<Integer, Row> rowMap = new ConcurrentHashMap<>();

    public InnerTable(XSSFSheet xssfSheet) {
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = xssfSheet.rowIterator();
        rowIterator.forEachRemaining(row -> {
            int rowIndex = row.getRowNum();
            Map<Integer, Cell> colCellMap = new ConcurrentHashMap<>();
            Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
            cellIterator.forEachRemaining(cell -> {
                colCellMap.put(cell.getColumnIndex(), new Cell(cell));
            });
            rowMap.put(row.getRowNum(), new Row(colCellMap));
        });
    }

    /**
     * get the specified row by row-index
     */
    public Row getRow(int rowIndex) {
        return rowMap.get(rowIndex);
    }
}
