
# Chimm.Excel —— 设置模板，填充数据，就完事儿了~
<p align="left">
	<img src='https://img.shields.io/badge/License-Apache--2.0-brightgreen' alt='lisence'></img>
	<img src="https://img.shields.io/badge/JDK-1.8-9cf" alt='jdk'></img>
	<img src="https://img.shields.io/badge/Apache--POI-4.1.2-blue" alt='poi'></img>
	<img src="https://img.shields.io/badge/Antlr-4-critical" alt='antlr4'></img>
</p>

```

            _____ _     _                       _____             _ 
           /  __ \ |   (_)                     |  ___|           | |
           | /  \/ |__  _ _ __ ___  _ __ ___   | |____  _____ ___| |
           | |   | '_ \| | '_ ` _ \| '_ ` _ \  |  __\ \/ / __/ _ \ |
           | \__/\ | | | | | | | | | | | | | |_| |___>  < (_|  __/ |
            \____/_| |_|_|_| |_| |_|_| |_| |_(_)____/_/\_\___\___|_|

```

## 1. 项目介绍
1. `Chimm.Excel` 是什么？  
该程序是一个基于 Apache POI 和 Antlr4 打造的 excel 生成的 Java 工具，主要作用在于减少了开发人员通过程序生成表格的工作量，提高了工作效率。

2. `Chimm.Excel` 的优势是什么？
	- 和网上部分的开源软件的区别是，该程序是基于 excel 模板进行工作的，并非基于注解。也就是说，我们首先需要在 office 中编写好我们的 excel 模板才行，优势在于，我们可以直接在 office 中就直接定制化表格的样式，也就是说，样式直接由 excel 软件进行控制了，这大大降低了我们在程序中操作样式的时间。
	- 由于该程序是基于 `Antlr4` 进行开发的，所以在变量定义上面，非常的灵活，我们可以定义集合变量，甚至我们还可以在公式中定义变量。一个表格对应一个数据对象，开发人员只需查询数据、组装数据即可。
    - 可以自定义模板样式，该程序也提供了部分操作表格样式的功能，如：复制一行、合并单元格、更改边框样式等等。
    - 主要的重点功能暂时介绍这么多，这些功能基本能解决绝大部分问题了。如果各位遇到了其他需要解决的应用场景，欢迎评论提出 `issue`，我们一起探讨。

3. excel 模板如何生成？  
模板生成非常的简单，我们定义变量的时候，只需要使用 `$` + `大小括号` 包围的形式即可，如：`${school.name}`。

4. `Chimm.Excel` 功能简介（v1.0）
	- [x] 导出excel二进制文件
	- [x] 根据模板中的变量，将值写入
	- [x] 支持公式
    	- [x] 支持带变量的公式，如：`SUM(A1,A2,${demo.value})`
	- [x] 操作表格添加/减少行
    	- [x] ⭐️添加行会自动更新公式
	- [x] 合并单元格
	- [x] 更改单元格边框样式（加粗、虚线等）

## 2. 功能展示
### 2.1 demo文件演示

我提供了一个 `demo` 测试类。  
模板具体位置： `src/test/resources/demo.xlsx`   
测试类的具体位置：`src/test/java/../demo/Demo.java`

**模板文件：**
![模板文件](./images/template.png)

**处理后的文件：**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201020225049632.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoaW1taHVhbmc=,size_16,color_FFFFFF,t_70#pic_center)


### 2.2 详细操作说明
1. wiki（GitHub）：[点击前往](https://github.com/chimmhuang/chimm.excel/wiki)
2. wiki（码云）：[点击前往](https://gitee.com/chimmhuang/chimm.excel/wikis)
3. 我在 `src/test/java/../demo/Demo.java` 中，写了一个测试方法，该方法几乎包含了最常用的方法，可以提供参照。

## 3. 起步
1. 配置模板  
我在该项目中提功能 demo 使用的模板，基本满足绝大部分场景：`src/test/resources/demo.xlsx`

2. 导入坐标
```xml
<dependency>
    <groupId>com.github.chimmhuang</groupId>
    <artifactId>chimm.excel</artifactId>
    <version>1.0</version>
</dependency>
```

3. 入门程序
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

    // ... 填充数据 ...
    tableData.setTitle("xx中学成绩单");
    // ... 省略了添加数据的代码 ...
    
    // 将封装好的表格对象，填充到 excel 表格中
    ExcelHelper.fillInData(table, tableData);

    // 将表格对象转换为二进制，resultBytes 即是最终想要的结果
    byte[] resultBytes = ExcelHelper.convert2Byte(table);
}
```

## 4. 参与贡献
非常欢迎你的加入！[提一个 Issue](https://github.com/chimmhuang/chimm.excel/issues/new) 或者提交一个 Pull Request。

##  5. 联系作者
`QQ & 微信`：`905369866`
`email`：`chimmhuang@163.com`

## 6. 开源协议
[Apache 2.0](LICENSE) © Chimm Huang