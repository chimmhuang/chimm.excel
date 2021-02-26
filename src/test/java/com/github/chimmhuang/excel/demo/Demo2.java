package com.github.chimmhuang.excel.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

import com.github.chimmhuang.excel.ExcelHelper;
import com.github.chimmhuang.excel.parser.MapParser;
import com.github.chimmhuang.excel.tablemodel.ExcelWorkbook;
import com.github.chimmhuang.excel.tablemodel.SheetTable;

/**
 * @author Chimm Huang
 */
public class Demo2 {
	public static void main(String[] args) {
		try {
			String xlsFile = "src/test/resources/demo_map.xlsx";
			   // 获取桌面路径
			
	        FileSystemView fsv = FileSystemView.getFileSystemView();
	        String desktop = fsv.getHomeDirectory().getPath();
	        String outFile = desktop + "/template_map.xlsx";
			testFillInTable(xlsFile, outFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFillInTable(String xlsFile, String outFile) throws Exception {
		// 获取 excel 二进制文件
		File file = new File(xlsFile);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		// 创建 table 对象
		ExcelWorkbook excelWorkbook = ExcelHelper.createWorkbook(bytes);
		SheetTable table = excelWorkbook.getSheet(0);
		// 设置 自定义 sheet 页名称
		table.setSheetName("成绩表");

		MapParser map = new MapParser();
		List<Map<String, Object>> score = new ArrayList<Map<String, Object>>() {
			{
				add(new HashMap<String, Object>() {
					{
						put("chinese", 100);
						put("english", 100);
						put("math", 100);
						put("class", "三年2班");
						put("name", "陈小小");
					}
				});
				add(new HashMap<String, Object>() {
					{
						put("chinese", 100);
						put("english", 99);
						put("math", 100);
						put("class", "三年1班");
						put("name", "陈小二");
					}
				});
			}
		};

		map.put("score", score);
		map.put("title", "测试");
		// 将变量的值填充进表格
		ExcelHelper.fillInMapData(table, map);

		// 获取转换后的二进制
		byte[] bytes1 = ExcelHelper.convert2Byte(table);

		// 获取桌面路径
		File targetFile = new File(outFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		fos.write(bytes1);
		fos.close();
	}
}