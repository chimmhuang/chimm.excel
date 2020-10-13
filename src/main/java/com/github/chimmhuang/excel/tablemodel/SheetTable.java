package com.github.chimmhuang.excel.tablemodel;

import com.github.chimmhuang.excel.parser.VariableParserBaseVisitor;
import com.github.chimmhuang.excel.parser.VariableParserLexer;
import com.github.chimmhuang.excel.parser.VariableParserParser;
import com.github.chimmhuang.excel.parser.VariableParserParser.ArrayContext;
import com.github.chimmhuang.excel.ExcelHelper;
import com.github.chimmhuang.excel.parser.VariableParserParser.ExprListContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.NameContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类对应于 Excel 的 [sheet页]
 * This class corresponds to the [sheet page] of Excel.
 *
 * @author Chimm Huang
 */
public class SheetTable implements Iterable<Cell> {

    /**
     * 行信息存储在这里
     * row information is stored here
     *
     * key - 对应 Excel 上的 "行号"，从 1 开始。
     *       row-number. start from 1
     *
     * value - Row{@link Row}
     */
    private final Map<Integer, Row> rowMap = new ConcurrentHashMap<>();

    /**
     * 单元格的宽度存储在这里，开发者无需操作该属性
     * the width of the cell is stored here, developers do not need to manipulate this attribute
     *
     * key - 列的索引，从 0 开始（Apache poi 行号和列号都是从 0 开始的）
     *       col-index. start from 0 (Apache poi row and column numbers start from 0)
     *
     * value - 列的宽度
     *         col-width
     */
    private final Map<Integer, Integer> colWidthMap = new ConcurrentHashMap<>();

    /**
     * 对应 excel 的 sheet 名称
     * sheet name in excel
     */
    private final String sheetName;

    /**
     * excel 表格的最后一行行号
     * 当该对象被初始化时，会更新此属性的值
     *
     * the last row num in excel.
     * when the object is instantiated the value of the variable will be updated.
     */
    private int lastRowNum = 0;

    /**
     * 数字正则匹配
     * regex to match number
     */
    private final Pattern numberMatch = Pattern.compile("\\d+");

    public SheetTable(XSSFSheet xssfSheet) {

        List<CellRangeAddress> mergedRegions = xssfSheet.getMergedRegions();

        sheetName = xssfSheet.getSheetName();
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = xssfSheet.rowIterator();
        rowIterator.forEachRemaining(row -> {
            int rowIndex = row.getRowNum();
            lastRowNum = Math.max(lastRowNum, rowIndex + 1);
            Map<String, Cell> colCellMap = new ConcurrentHashMap<>();
            Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
            cellIterator.forEachRemaining(cell -> {

                int columnIndex = cell.getColumnIndex();

                // get the merged region
                CellRangeAddress cellRangeAddress = mergedRegions.stream()
                        .filter(cellAddresses -> cellAddresses.getFirstRow() == rowIndex && cellAddresses.getFirstColumn() == columnIndex)
                        .findFirst()
                        .orElse(null);

                colCellMap.put(ExcelHelper.getColName(columnIndex), new Cell((XSSFCell) cell, cellRangeAddress));
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
     * 获取行
     * get the specified row by row-number
     */
    public Row getRow(int rowNum) {
        return rowMap.get(rowNum);
    }

    /**
     * 删除行号大于等于指定 rowNum 的行
     * remove rows which row-num greater than or equals the specified row-num
     *
     * @param rowNum specified row-num
     */
    public void removeRowGE(int rowNum) {
        rowMap.keySet().stream()
                .filter(key -> key >= rowNum)
                .forEach(rowMap::remove);
        lastRowNum = rowNum - 1;
    }

    /**
     * 在表格最后添加一行
     * append a row at the end
     *
     * @param srcRow source row{@link Row}
     * @return desc row
     */
    public Row appendRow(Row srcRow) {

        lastRowNum++;
        Row descRow = srcRow.copy();

        // update row num
        descRow.setRowNum(lastRowNum);
        descRow.iterator().forEachRemaining(cell -> {
            cell.setRow(lastRowNum);

            int srcRowNum = srcRow.getRowNum();
            int descRowNum = descRow.getRowNum();
            int subtractRowNum = descRowNum - srcRowNum;

            MergedRegion mergedRegion = cell.getMergedRegion();
            if (mergedRegion != null) {
                // update row num
                mergedRegion.setFirstRowRum(descRowNum);
                mergedRegion.setLastRowRum(mergedRegion.getLastRowRum() + subtractRowNum);
                cell.setMergedRegion(mergedRegion);
            }

            // update formula row num
            if (cell.getCellType().equals(CellType.FORMULA) && cell.getValue() != null) {

                String oldFormula = cell.getValue().toString();

                // lexical analysis
                VariableParserLexer lexer = new VariableParserLexer(CharStreams.fromString(oldFormula));
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // syntax analysis
                VariableParserParser parser = new VariableParserParser(tokens);

                String newFormula = parser.formula().exprList().accept(new VariableParserBaseVisitor<String>() {
                    @Override
                    public String visitExprList(ExprListContext ctx) {
                        String oldExprList = ctx.getText();
                        String newExprList = super.visitExprList(ctx);
                        return oldFormula.replaceAll(oldExprList, newExprList);
                    }

                    /**
                     * array
                     * e.g. B4:B5
                     */
                    @Override
                    public String visitArray(ArrayContext ctx) {
                        String arrayText = ctx.getText();
                        for (TerminalNode terminalNode : ctx.IDENTIFIER()) {
                            String identifierText = terminalNode.getText();
                            Matcher matcher = numberMatch.matcher(identifierText);
                            if (matcher.find()) {
                                int srcRowNum = Integer.parseInt(matcher.group());
                                int descRowNum = srcRowNum + subtractRowNum;
                                String replaceText = identifierText.replaceAll(Integer.toString(srcRowNum), Integer.toString(descRowNum));
                                arrayText = arrayText.replaceAll(identifierText, replaceText);
                            }
                        }
                        return arrayText;
                    }

                    /**
                     * name
                     * e.g. B4
                     */
                    @Override
                    public String visitName(NameContext ctx) {
                        String nameTest = ctx.getText();
                        String identifierText = ctx.qualifiedName().IDENTIFIER(0).getText();
                        Matcher matcher = numberMatch.matcher(identifierText);
                        if (matcher.find()) {
                            int srcRowNum = Integer.parseInt(matcher.group());
                            int descRowNum = srcRowNum + subtractRowNum;
                            String replaceText = identifierText.replaceAll(Integer.toString(srcRowNum), Integer.toString(descRowNum));
                            nameTest = nameTest.replaceAll(identifierText, replaceText);
                        }
                        return nameTest;
                    }
                });
                cell.setFormula(newFormula);
            }
        });

        rowMap.put(lastRowNum, descRow);
        return descRow;
    }
}
