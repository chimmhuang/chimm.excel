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
    - [x] 支持带变量的公式，如：`SUM(A1,A2,${demo.value})`
- [x] 操作表格添加/减少行
    - [x] ⭐️添加行会自动更新公式
- [x] 合并单元格
- [x] 更改单元格边框样式（加粗、虚线等）

优点：
- 通过模板的方式动态生成 excel 表格，许多复杂样式直接在 excel 程序里设置完毕即可，减少代码层面操作。
- 通用新建数据对象的方式填充模板数据，一个表格对应一个数据对象，开发人员只需查询数据、组装数据即可。
- 可以自定义模板，灵活控制表格的新增行、减少行、合并行等。
- 支持变量公式。

如何制作模板？非常简单，使用`$`符号定义变量即可，如： `${demo.list[0].name}`，具体可以参照`src/test/java/resources/demo.xlsx`

## 2. 程序演示

### 2.1 demo文件演示
我提供了一个 `demo` 测试类。  
模板具体位置： `src/test/resources/demo.xlsx`   
测试类的具体位置：`src/test/java/../demo/Demo.java`

### 2.2 详细操作说明
wiki（GitHub）：todo...
wiki（码云）：todo...
CSDN：todo...


## 3. 起步
1. 配置模板
2. 导入坐标
```
// todo...
```
3. 如何使用？
```java
@Test
public void testFillInTable() throws Exception {
    // 获取文件的二进制
    File file = new File("src/test/resources/demo.xlsx");
    byte[] bytes = FileUtils.readFileToByteArray(file);

    // 通过 ExcelHelper 获取 excel 表格对象
    ExcelWorkbook excelWorkbook = ExcelHelper.createWorkbook(bytes);

    // 获取指定的 sheet 页
    SheetTable table = excelWorkbook.getSheet(0);

    // 封装表格数据对象
    SchoolReportData tableData = new SchoolReportData();

    tableData.setTitle("中和中学成绩单");
    // ... 填充数据 ...
    // ...
    
    // 将封装好的表格对象，填充到 excel 表格中
    ExcelHelper.fillInData(table, tableData);

    // 将表格对象转换为二进制，resultBytes 即是最终想要的结果
    byte[] resultBytes = ExcelHelper.convert2Byte(table);
}
```

## 4. 参与贡献
非常欢迎你的加入！[提一个 Issue](https://github.com/chimmhuang/chimm.excel/issues/new) 或者提交一个 Pull Request。

## 5. 开源协议
[Apache 2.0](LICENSE) © Chimm Huang