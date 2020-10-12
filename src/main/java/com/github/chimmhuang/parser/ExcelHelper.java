package com.github.chimmhuang.parser;

import com.github.chimmhuang.antlr.VariableParserLexer;
import com.github.chimmhuang.antlr.VariableParserParser;
import com.github.chimmhuang.antlr.VariableParserParser.ArrayIdxContext;
import com.github.chimmhuang.antlr.VariableParserParser.VariableContext;
import com.github.chimmhuang.exception.ConvertException;
import com.github.chimmhuang.exception.InvokeMethodException;
import com.github.chimmhuang.exception.ReflectionException;
import com.github.chimmhuang.tablemodel.Cell;
import com.github.chimmhuang.tablemodel.CellStyle;
import com.github.chimmhuang.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.tablemodel.Font;
import com.github.chimmhuang.tablemodel.SheetTable;
import com.github.chimmhuang.tablemodel.Row;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Chimm Huang
 */
public class ExcelHelper {

    private ExcelHelper() { }

    private static final char[] COL_SET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String getColName(Integer index) {
        StringBuilder sb = new StringBuilder();
        int length = COL_SET.length;
        int idx = index + 1;
        while (idx > length) {
            idx = idx / length;
            long b = idx % length;
            sb.append(COL_SET[(int) b - 1]);
        }
        sb.append(COL_SET[idx - 1]);
        return sb.reverse().toString();
    }


    public static Integer getColIndex(String name) {
        String colSet = new String(COL_SET);
        char[] chars = name.toCharArray();
        int result = 0;
        for (int index = chars.length - 1; index >= 0; index--) {
            char ch = chars[index];
            int i = colSet.indexOf(ch);
            result += (i + 1) * Math.pow(chars.length, chars.length - index - 1d);
        }
        return result - 1;
    }

    /**
     * create excel workbook
     *
     * @param bytes excel binary file
     */
    public static ExcelWorkbook createWorkbook(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new ExcelWorkbook(xssfWorkbook);
    }

    /**
     * get inner table
     *
     * @param bytes      excel binary file
     * @param sheetIndex sheet index
     */
    public static SheetTable getSheetInnerTable(byte[] bytes, int sheetIndex) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new SheetTable(xssfWorkbook.getSheetAt(sheetIndex));
    }

    /**
     * get inner table
     *
     * @param bytes     excel binary file
     * @param sheetName sheet name
     */
    public static SheetTable getSheetInnerTable(byte[] bytes, String sheetName) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new SheetTable(xssfWorkbook.getSheet(sheetName));
    }

    /**
     * fill variables into excel table
     *
     * @param table excel sheet table
     * @param data  table data
     */
    public static void fillInData(SheetTable table, Object data) {
        for (Cell cell : table) {
            Object value = cell.getValue();

            // insert value
            if (value instanceof String && ((String) value).startsWith("$")) {
                Object propValue = parseCellVariable(data, (String) value);
                cell.setValue(propValue);
            }

            // insert formula
            if (value instanceof String && ((String) value).startsWith("=")) {
                cell.setCellFormula((String) value);
            }
        }
    }

    /**
     * convert excel table{@link SheetTable} to byte
     * @param table inner table
     */
    public static byte[] convert2Byte(SheetTable table) {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet(table.getSheetName());

        // set sheet style
        Map<Integer, Integer> colWidthMap = table.getColWidthMap();
        List<CellRangeAddress> mergedRegions = table.getMergedRegions();
        colWidthMap.forEach(xssfSheet::setColumnWidth);
        mergedRegions.forEach(xssfSheet::addMergedRegion);

        Iterator<Row> rowIterator = table.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            org.apache.poi.ss.usermodel.Row xssfRow = getOrCreateRow(row.getRowNum(), xssfSheet);

            // set row style
            xssfRow.setHeight(row.getHeight());
            xssfRow.setHeightInPoints(row.getHeightInPoints());
            xssfRow.setZeroHeight(row.getZeroHeight());

            for (Cell cell : row) {
                CellType cellType = cell.getCellType();
                org.apache.poi.ss.usermodel.Cell xssfCell = xssfRow.createCell(getColIndex(cell.getCol()), cellType);

                Object value = cell.getValue();

                // set cell value
                switch (cellType) {
                    case BLANK:
                        xssfCell.setBlank();
                        break;
                    case FORMULA:
                        if (value != null) {
                            String formula = value.toString().replace("=", "");
                            xssfCell.setCellFormula(formula);
                        }
                        break;
                    default:
                        if (value != null) {
                            switch (value.getClass().getName()) {
                                case "java.lang.Double":
                                    xssfCell.setCellValue((Double) value);
                                    break;
                                case "java.math.BigDecimal":
                                    xssfCell.setCellValue(((BigDecimal) value).doubleValue());
                                    break;
                                case "java.lang.String":
                                    xssfCell.setCellValue(value.toString());
                                    break;
                                case "java.lang.Boolean":
                                    xssfCell.setCellValue((Boolean) value);
                                    break;
                                case "java.util.Date":
                                    xssfCell.setCellValue((Date) value);
                                    break;
                                case "java.util.Calendar":
                                    xssfCell.setCellValue((Calendar) value);
                                    break;
                                case "java.time.LocalDate":
                                    xssfCell.setCellValue((LocalDate) value);
                                    break;
                                case "java.time.LocalDateTime":
                                    xssfCell.setCellValue((LocalDateTime) value);
                                    break;
                                case "org.apache.poi.ss.usermodel.RichTextString":
                                    xssfCell.setCellValue((RichTextString) value);
                                    break;
                                default:break;
                            }
                        }
                        break;
                }

                // set cell style
                org.apache.poi.ss.usermodel.CellStyle xssfCellStyle = toExcelCellStyle(xssfWorkbook, cell.getCellStyle());
                xssfCell.setCellStyle(xssfCellStyle);
            }
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // convert xssfWorkbook to binary file
            xssfWorkbook.setForceFormulaRecalculation(true);
            xssfWorkbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new ConvertException(e);
        }
    }

    /**
     * convert {@link CellStyle} to {@link org.apache.poi.ss.usermodel.CellStyle}
     */
    private static org.apache.poi.ss.usermodel.CellStyle toExcelCellStyle(XSSFWorkbook xssfWorkbook, CellStyle cellStyle) {
        XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();

        xssfCellStyle.setBorderTop(cellStyle.getBorderTopEnum());
        xssfCellStyle.setBorderBottom(cellStyle.getBorderBottomEnum());
        xssfCellStyle.setBorderLeft(cellStyle.getBorderLeftEnum());
        xssfCellStyle.setBorderRight(cellStyle.getBorderRightEnum());

        xssfCellStyle.setTopBorderColor(cellStyle.getTopBorderColor());
        xssfCellStyle.setBottomBorderColor(cellStyle.getBottomBorderColor());
        xssfCellStyle.setLeftBorderColor(cellStyle.getLeftBorderColor());
        xssfCellStyle.setRightBorderColor(cellStyle.getRightBorderColor());

        xssfCellStyle.setFillBackgroundColor(cellStyle.getFillBackgroundColor());
        xssfCellStyle.setFillForegroundColor(cellStyle.getFillForegroundColor());

        xssfCellStyle.setDataFormat(cellStyle.getDataFormat());
        xssfCellStyle.setHidden(cellStyle.getHidden());
        xssfCellStyle.setLocked(cellStyle.getLocked());

        xssfCellStyle.setAlignment(cellStyle.getAlignmentEnum());
        xssfCellStyle.setVerticalAlignment(cellStyle.getVerticalAlignmentEnum());

        Font font = cellStyle.getFont();
        if (font != null) {
            XSSFFont xssfFont = xssfWorkbook.createFont();

            xssfFont.setBold(font.getBold());
            xssfFont.setCharSet(font.getCharSet());
            xssfFont.setColor(font.getColor());
            xssfFont.setFamily(font.getFamily());
            xssfFont.setFontHeight(font.getFontHeight());
            xssfFont.setFontHeightInPoints(font.getFontHeightInPoints());
            xssfFont.setFontName(font.getFontName());
            xssfFont.setItalic(font.getItalic());
            xssfFont.setScheme(font.getScheme());
            xssfFont.setStrikeout(font.getStrikeout());
            xssfFont.setThemeColor(font.getThemeColor());
            xssfFont.setTypeOffset(font.getTypeOffset());
            xssfFont.setUnderline(font.getUnderline());

            xssfCellStyle.setFont(xssfFont);
        }

        return xssfCellStyle;
    }

    /**
     * get XSSFRow {@link XSSFRow}. if it's not exist, then create
     * @param rowNum row-num, start from 1
     * @param xssfSheet {@link XSSFSheet}
     * @return {@link XSSFRow}
     */
    private static org.apache.poi.ss.usermodel.Row getOrCreateRow(int rowNum, XSSFSheet xssfSheet) {
        int rowIndex = rowNum - 1;
        XSSFRow row = xssfSheet.getRow(rowIndex);
        if (row == null) {
            row = xssfSheet.createRow(rowIndex);
        }
        return row;
    }

    /**
     * parse the variable name filled in the cell
     *
     * @param data             table data
     * @param cellVariableName the variable name filled in the cell
     * @return the value corresponding to the table data
     */
    private static Object parseCellVariable(Object data, String cellVariableName) {
        // lexical analysis
        VariableParserLexer lexer = new VariableParserLexer(CharStreams.fromString(cellVariableName));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // syntax analysis
        VariableParserParser parser = new VariableParserParser(tokens);

        Object propValue = data;
        for (VariableContext variableContext : parser.variableExpr().variable()) {
            String variableName = variableContext.IDENTIFIER().getText();
            propValue = getPropValue(propValue, variableName);
            if (propValue == null) {
                return null;
            }

            // if the prop instanceof List, then get the value under a specific index.
            List<ArrayIdxContext> arrayIdxContexts = variableContext.arrayIdx();
            for (ArrayIdxContext arrayIdxContext : arrayIdxContexts) {
                int index = Integer.parseInt(arrayIdxContext.NUMBER().getSymbol().getText());
                if (propValue instanceof List) {
                    List propValueList = (List) propValue;
                    propValue = propValueList.get(index);
                } else {
                    throw new IllegalArgumentException(propValue.getClass().getName() + "must be List.");
                }
            }
        }

        return propValue;
    }

    /**
     * get the value of the specified attribute of the incoming data
     *
     * @param obj      object
     * @param propName object attribute name
     * @return the value of the specified attribute of the incoming data
     */
    private static Object getPropValue(Object obj, String propName) {
        if (obj == null || propName == null) {
            return null;
        }

        Field declaredField = null;
        try {
            declaredField = obj.getClass().getDeclaredField(propName);
        } catch (NoSuchFieldException e) {
            // try to get the parent class
            try {
                declaredField = obj.getClass().getSuperclass().getDeclaredField(propName);
            } catch (NoSuchFieldException noSuchFieldException) {
                throw new ReflectionException(noSuchFieldException);
            }
        }

        try {
            PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), obj.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(obj);
        } catch (Exception e) {
            throw new InvokeMethodException(e);
        }
    }
}
