package com.github.chimmhuang.parser;

import com.github.chimmhuang.antlr.VariableParserLexer;
import com.github.chimmhuang.antlr.VariableParserParser;
import com.github.chimmhuang.antlr.VariableParserParser.ArrayIdxContext;
import com.github.chimmhuang.antlr.VariableParserParser.VariableContext;
import com.github.chimmhuang.tablemodel.Cell;
import com.github.chimmhuang.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.tablemodel.InnerTable;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Chimm Huang
 */
public class TableDataHelper {

    private TableDataHelper() { }

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
    public static InnerTable getSheetInnerTable(byte[] bytes, int sheetIndex) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new InnerTable(xssfWorkbook.getSheetAt(sheetIndex));
    }

    /**
     * get inner table
     *
     * @param bytes     excel binary file
     * @param sheetName sheet name
     */
    public static InnerTable getSheetInnerTable(byte[] bytes, String sheetName) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
        return new InnerTable(xssfWorkbook.getSheet(sheetName));
    }

    /**
     * fill variables into excel table
     *
     * @param table excel sheet table
     * @param data  table data
     */
    public static void fillInData(InnerTable table, Object data) {
        for (Cell cell : table) {
            Object value = cell.getValue();

            // insert value
            if (value instanceof String && ((String) value).startsWith("$")) {
                Object propValue = parseCellVariable(data, (String) value);
                cell.setValue(propValue);
            }

            // insert formula
        }
    }

    /**
     * convert excel workbook to byte
     */
    public static byte[] convert2Byte(ExcelWorkbook workbook) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.getXssfWorkbook().write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                throw new RuntimeException(noSuchFieldException);
            }
        }

        try {
            PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(), obj.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
