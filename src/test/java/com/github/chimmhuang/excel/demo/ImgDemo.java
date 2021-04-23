package com.github.chimmhuang.excel.demo;

import com.github.chimmhuang.excel.ExcelHelper;
import com.github.chimmhuang.excel.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.excel.tablemodel.SheetTable;
import com.github.chimmhuang.excel.util.FileUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Chimm Huang
 */
public class ImgDemo {

    @Test
    public void testImg() throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();

        FileInputStream fis = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\head.jpg"));


        byte[] bytes = IOUtils.toByteArray(fis);

        int picture = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        fis.close();

        XSSFCreationHelper creationHelper = workbook.getCreationHelper();
        XSSFSheet sheet = workbook.createSheet("test");

        XSSFDrawing drawingPatriarch = sheet.createDrawingPatriarch();
        // 40像素
        sheet.createRow(1).setHeightInPoints((float) Units.pixelToPoints(40));

//        sheet.setColumnWidth(1, (int) (44.13 * 256));
        sheet.setColumnWidth(1, (int) (40 * 256  / Units.DEFAULT_CHARACTER_WIDTH));
//        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 2));

        XSSFClientAnchor clientAnchor = drawingPatriarch.createAnchor(0, 0, 0, 0, 1, 1, 2, 2);
        clientAnchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        drawingPatriarch.createPicture(clientAnchor, picture);


        float heightInPoints = sheet.getRow(1).getHeightInPoints();
        float columnWidthInPixels = sheet.getColumnWidthInPixels(1);
        System.out.printf("------- heightInPoints: %s -------- columnWidthInPixels: %s%n", heightInPoints, columnWidthInPixels);
        short height = sheet.getRow(1).getHeight();
        int columnWidth = sheet.getColumnWidth(1);
        System.out.printf("------- height: %s -------- columnWidth: %s%n", heightInPoints, columnWidth);



        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\temp.xlsx");
        // 写入excel文件
        workbook.write(fileOutputStream);
    }

    @Test
    public void testRead() throws Exception {
        // 获取 excel 二进制文件
        File file = new File("C:\\Users\\admin\\Desktop\\temp.xlsx");
        byte[] bytes = FileUtils.readFileToByteArray(file);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);

        XSSFSheet sheet = xssfWorkbook.getSheet("test");
        XSSFRow row = sheet.getRow(1);
        short height = row.getHeight();
        int columnWidth = sheet.getColumnWidth(1);

        List<XSSFShape> shapes = sheet.getDrawingPatriarch().getShapes();
        for (XSSFShape shape : shapes) {
            XSSFAnchor anchor = shape.getAnchor();
        }

        System.out.printf("------- height: %s -------- width: %s%n", height, columnWidth);


    }
}
