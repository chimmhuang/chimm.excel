package com.github.chimmhuang.tablemodel;

import org.apache.poi.ss.usermodel.RichTextString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * This class corresponds to each [cell] of excel.
 *
 * @author Chimm Huang
 */
public class Cell {

    private final org.apache.poi.ss.usermodel.Cell cell;

    public Cell(org.apache.poi.ss.usermodel.Cell cell) {
        this.cell = cell;
    }

    public Object getValue() {
        switch (cell.getCellType()) {
            default:
            case _NONE:
            case STRING:
            case FORMULA:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case BLANK:
                return null;
        }
    }

    public void setValue(Object value) {
        if (value == null) {
            return;
        }

        if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof String) {
            cell.setCellValue(value.toString());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar) value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue((LocalDate) value);
        } else if (value instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) value);
        } else if (value instanceof RichTextString) {
            cell.setCellValue((RichTextString) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }
}
