package com.github.chimmhuang.excel;

import com.github.chimmhuang.excel.parser.DataVariableParserVisitor;
import com.github.chimmhuang.excel.parser.VariableParserLexer;
import com.github.chimmhuang.excel.parser.VariableParserParser;
import com.github.chimmhuang.excel.tablemodel.Cell;
import com.github.chimmhuang.excel.tablemodel.CellStyle;
import com.github.chimmhuang.excel.tablemodel.Font;
import com.github.chimmhuang.excel.tablemodel.SheetTable;
import com.github.chimmhuang.excel.parser.VariableParserParser.ArrayIdxContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.VariableContext;
import com.github.chimmhuang.excel.exception.ConvertException;
import com.github.chimmhuang.excel.exception.InvokeMethodException;
import com.github.chimmhuang.excel.exception.ReflectionException;
import com.github.chimmhuang.excel.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.excel.tablemodel.MergedRegion;
import com.github.chimmhuang.excel.tablemodel.Row;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
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
        int idx = index;
        while (idx > length - 1) {
            idx = idx / length;
            sb.append(COL_SET[idx - 1]);
        }
        sb.append(COL_SET[index % length]);
        return sb.toString();
    }

    public static Integer getColIndex(String name) {
        String colSet = new String(COL_SET);
        char[] chars = name.toCharArray();
        int result = 0;
        for (int index = chars.length - 1; index >= 0; index--) {
            char ch = chars[index];
            int i = colSet.indexOf(ch);
            result += (i + 1) * Math.pow(COL_SET.length, chars.length - index - 1d);
        }
        return result - 1;
    }

    /**
     * 创建 excel 工作簿
     * create excel workbook
     *
     * @param bytes excel 的二进制文件
     *              excel binary file
     */
    public static ExcelWorkbook createWorkbook(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new ExcelWorkbook(xssfWorkbook);
    }

    /**
     * 获取 sheet 页的表格信息
     * get the sheet table with index 0
     *
     * @param bytes      excel binary file
     */
    public static SheetTable getSheetTable(byte[] bytes) throws IOException {
        return getSheetTable(bytes, 0);
    }

    /**
     * 获取 sheet 页的表格信息
     * get sheet table
     *
     * @param bytes      excel binary file
     * @param sheetIndex sheet index, start from 0
     */
    public static SheetTable getSheetTable(byte[] bytes, int sheetIndex) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new SheetTable(xssfWorkbook.getSheetAt(sheetIndex));
    }

    /**
     * 获取 sheet 页的表格信息
     * get sheet table
     *
     * @param bytes     excel binary file
     * @param sheetName sheet name
     */
    public static SheetTable getSheetTable(byte[] bytes, String sheetName) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new SheetTable(xssfWorkbook.getSheet(sheetName));
    }

    /**
     * 将变量填充入表格
     * fill variables into excel table
     *
     * @param table excel sheet table
     * @param data  table data
     */
    public static void fillInData(SheetTable table, Object data) {

        DataVariableParserVisitor visitor = new DataVariableParserVisitor(data);

        for (Cell cell : table) {
            Object value = cell.getValue();
            CellType cellType = cell.getCellType();

            if (cellType.equals(CellType.FORMULA) && value != null) {
                // insert formula
                VariableParserLexer lexer = new VariableParserLexer(CharStreams.fromString(value.toString()));
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // syntax analysis
                VariableParserParser parser = new VariableParserParser(tokens);

                String newFormula = (String) parser.expr().accept(visitor);

                cell.setFormula(newFormula);
            } else if (value instanceof String && ((String) value).startsWith("$")) {
                // insert value
                Object propValue = parseCellVariable(data, (String) value);
                cell.setValue(propValue);
            }
        }
    }

    /**
     * 将 sheet 对象转换为 excel 二进制文件
     * convert excel table{@link SheetTable} to byte
     *
     * @param table sheet table
     * @return excel binary file
     */
    public static byte[] convert2Byte(SheetTable table) {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet(table.getSheetName());
        XSSFCreationHelper xssfCreationHelper = new XSSFCreationHelper(xssfWorkbook);

        // set sheet style
        Map<Integer, Integer> colWidthMap = table.getColWidthMap();
        colWidthMap.forEach(xssfSheet::setColumnWidth);

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
                                case "java.lang.Integer":
                                    xssfCell.setCellValue((Integer) value);
                                    break;
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
                                default:
                                    break;
                            }
                        }
                        break;
                }

                // set cell style
                org.apache.poi.ss.usermodel.CellStyle xssfCellStyle = toExcelCellStyle(xssfWorkbook, cell.getCellStyle());
                xssfCell.setCellStyle(xssfCellStyle);

                // set merged region
                MergedRegion mergedRegion = cell.getMergedRegion();
                if (mergedRegion != null) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(mergedRegion.getFirstRowNum() - 1, mergedRegion.getLastRowNum() - 1, getColIndex(mergedRegion.getFirstColName()), getColIndex(mergedRegion.getLastColName()));
                    xssfSheet.addMergedRegion(cellRangeAddress);
                }

                // set hyper link
                String hyperlink = cell.getHyperlink();
                HyperlinkType hyperlinkType = cell.getHyperlinkType();
                if (hyperlink != null && !"".equals(hyperlink) && hyperlinkType != null) {
                    XSSFHyperlink link = xssfCreationHelper.createHyperlink(hyperlinkType);
                    link.setAddress(hyperlink);
                    xssfCell.setHyperlink(link);
                }
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
     * 转换 cellStyle 对象为 Apache poi 的对象
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

        xssfCellStyle.setFillBackgroundColor(cellStyle.getFillBackgroundXSSFColor());
        xssfCellStyle.setFillForegroundColor(cellStyle.getFillForegroundXSSFColor());
        xssfCellStyle.setFillPattern(cellStyle.getFillPattern());

        xssfCellStyle.setDataFormat(cellStyle.getDataFormat());
        xssfCellStyle.setHidden(cellStyle.getHidden());
        xssfCellStyle.setLocked(cellStyle.getLocked());

        xssfCellStyle.setIndention(cellStyle.getIndention());
        xssfCellStyle.setWrapText(cellStyle.getWrapText());
        xssfCellStyle.setShrinkToFit(cellStyle.getShrinkToFit());
        xssfCellStyle.setReadingOrder(cellStyle.getReadingOrder());
        xssfCellStyle.setQuotePrefixed(cellStyle.getQuotePrefixed());
        xssfCellStyle.setRotation(cellStyle.getRotation());

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
     * 获取 XSSFRow 对象，若不存在，则创建一个
     * get XSSFRow {@link XSSFRow}. if it's not exist, then create
     *
     * @param rowNum    row-num, start from 1
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
     * 将 excel 里的变量转换为对应的值
     * parse the variable name filled in the cell
     *
     * @param data             table data
     * @param cellVariableName the variable name filled in the cell
     * @return the value corresponding to the table data
     */
    public static Object parseCellVariable(Object data, String cellVariableName) {
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
     * 获取表格数据对应变量的值
     * get the value of the specified attribute of the incoming data
     *
     * @param obj      表格数据对象
     *                 object
     * @param propName 表格数据对象的属性名称
     *                 object attribute name
     * @return 对应属性的值。the value of the specified attribute of the incoming data
     */
    private static Object getPropValue(Object obj, String propName) {

        if (obj == null || propName == null) {
            return null;
        }

        if (obj instanceof Map) {
            Map mapObj = (Map) obj;
            return mapObj.get(propName);
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
