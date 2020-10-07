# chimm.excel —— 设置模板，填充数据，就完事儿了~
<p align="left">
	<img src='https://img.shields.io/badge/License-Apache--2.0-brightgreen' alt='lisence'></img>
	<img src="https://img.shields.io/badge/JDK-1.8-9cf" alt='jdk'></img>
	<img src="https://img.shields.io/badge/Apache--POI-4.1.2-blue" alt='poi'></img>
	<img src="https://img.shields.io/badge/Antlr-4-critical" alt='antlr4'></img>
</p>

## 1. 项目介绍

该项目是基于 `Apache POI` 和 `Antlr4` 打造的 excel 生成的工具。我们需要准备什么？一个待填充数据的 excel 模板即可。

功能介绍：
- [x] 导出excel二进制文件
- [x] 根据模板中的变量，将值写入
- [x] 支持公式
- [ ] 支持带有变量的公式（如：=SUM(A1,A2,${demo.value})）
- [ ] 操作表格添加/减少一行（row）
- [ ] 合并单元格
- [ ] 更改单元格部分样式（如：单元格加粗）
- [ ] 更改单元格部分字体样式

应用场景：
- 导出表格数据
- 动态组装表格数据

优点：
- 可以自定义模板，可以更加灵活的控制表格样式
- 通用变量的方式操作模板
- 提供了 `POI` 原生操作方式

缺点：
- 稍微多一点工作量（基本都在模板设置上，设置好了就ok了）

如何制作模板？非常简单，使用`$`符号定义变量即可，如： `${demo.list[0].name}`，具体可以参照`src/test/java/resources/demo.xlsx`

## 2. 起步
1. 配置模板
2. 导入坐标
```
// 等待上传。。。
```
3. 如何使用？
```java
@Test
public void testFillInTable() throws Exception {
    // 获取文件的二进制
    File file = new File("src/test/resources/demo.xlsx");
    byte[] bytes = FileUtils.readFileToByteArray(file);

    // 通过 TableDataHelper 获取 excel 表格对象
    ExcelWorkbook excelWorkbook = ExcelHelper.createWorkbook(bytes);

    // 获取指定的 sheet 页
    InnerTable table = excelWorkbook.getSheet(0);

    // 封装表格数据对象
    SchoolReportData tableData = new SchoolReportData();

    tableData.setTitle("中和中学成绩单");
    // ... 填充数据 ...
    // ...
    
    // 将封装好的表格对象，填充到 excel 表格中
    ExcelHelper.fillInData(table, tableData);

    // 将表格对象转换为二进制，resultBytes 即是最终想要的结果
    byte[] resultBytes = ExcelHelper.convert2Byte(excelWorkbook);
}
```

## 3. 参与贡献
非常欢迎你的加入！[提一个 Issue](https://github.com/chimmhuang/chimm.excel/issues/new) 或者提交一个 Pull Request。

## 4. 开源协议
[Apache 2.0](LICENSE) © Chimm Huang