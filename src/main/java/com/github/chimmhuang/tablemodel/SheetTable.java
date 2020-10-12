package com.github.chimmhuang.tablemodel;

import com.github.chimmhuang.parser.ExcelHelper;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class corresponds to the [sheet page] of Excel.
 *
 * @author Chimm Huang
 */
public class SheetTable implements Iterable<Cell> {

    /**
     * key - row-number. start from 1
     * value - Row{@link Row}
     */
    private final Map<Integer, Row> rowMap = new ConcurrentHashMap<>();

    /**
     * key - col-index. start from 0
     * value - width
     */
    private final Map<Integer, Integer> colWidthMap = new ConcurrentHashMap<>();

    /**
     * sheet name in excel
     */
    private final String sheetName;

    /**
     * merged list of cells
     */
    private final List<CellRangeAddress> mergedRegions;

    /**
     * the last row num in excel.
     * when the object is instantiated, the value of the variable will be updated.
     */
    private int lastRowNum = 0;

    public SheetTable(XSSFSheet xssfSheet) {
        mergedRegions = xssfSheet.getMergedRegions();
        sheetName = xssfSheet.getSheetName();
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = xssfSheet.rowIterator();
        rowIterator.forEachRemaining(row -> {
            lastRowNum = Math.max(lastRowNum, row.getRowNum() + 1);
            Map<String, Cell> colCellMap = new ConcurrentHashMap<>();
            Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
            cellIterator.forEachRemaining(cell -> {
                int columnIndex = cell.getColumnIndex();
                colCellMap.put(ExcelHelper.getColName(columnIndex), new Cell((XSSFCell) cell));
                colWidthMap.put(columnIndex, xssfSheet.getColumnWidth(columnIndex));
            });
            Row descRow = new Row(row, colCellMap);
            rowMap.put(row.getRowNum() + 1, descRow);
        });
    }

    public String getSheetName() {
        return sheetName;
    }

    public int getLastRowNum() {
        return lastRowNum;
    }

    public Map<Integer, Integer> getColWidthMap() {
        return colWidthMap;
    }

    public List<CellRangeAddress> getMergedRegions() {
        return mergedRegions;
    }


    public Iterator<Row> rowIterator() {
        return rowMap.values().iterator();
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


    /**
     * get the specified row by row-number
     */
    public Row getRow(int rowNum) {
        return rowMap.get(rowNum);
    }

    /**
     * remove rows which row-num greater than or equals the specified row-num
     * @param rowNum specified row-num
     */
    public void removeRowGE(int rowNum) {
        rowMap.keySet().stream()
                .filter(key -> key >= rowNum)
                .forEach(rowMap::remove);
        lastRowNum = rowNum - 1;
    }

    /**
     * append a row at the end
     *
     * @param srcRow source row{@link Row}
     * @return desc row
     */
    public Row appendRow(Row srcRow) {

        lastRowNum++;
        Row descRow = SerializationUtils.clone(srcRow);

        // update row num
        descRow.setRowNum(lastRowNum);
        descRow.iterator().forEachRemaining(cell -> {
            cell.setRow(lastRowNum);

            if (cell.getCellType().equals(CellType.FORMULA)) {
                // todo: update formula
            }
        });

        rowMap.put(lastRowNum, descRow);
        return descRow;
    }
}
