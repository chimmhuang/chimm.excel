# [](https://github.com/chimmhuang/chimm.excel/compare/v1.3.0...v) (2021-03-25)


### Bug Fixes

* 修复 list 可能存在取值超出索引报错的问题 ([aa92992](https://github.com/chimmhuang/chimm.excel/comm2840c7a5c98a5322d070802160))


### Features

* 支持多个 sheet 表格导出 ([5b86cf1](https://gitee.com/chimmhuang/chimm.excel/commit/5b86cf1896a060f1d8d2d39fd4e6366a)), closes [#I3C8SY](https://gitee.com/chimmhuang/chimm.excel/issues/I3C8SY)

# [](https://github.com/chimmhuang/chimm.excel/compare/v1.1.0...v) (2021-01-27)


### Bug Fixes

* 修复公式获取错误的问题 ([2302192](https://github.com/chimmhuang/chimm.excel/commit/23021928df4bb63324be29b6de134443880ddde3)), closes [#I2M861](https://github.com/chimmhuang/chimm.excel/issues/I2M861)


### Features

* 新增单元格批量合并的功能 ([118583c](https://github.com/chimmhuang/chimm.excel/commit/118583c2c11a816a9d6027d617581bfb87250bae)), closes [#7](https://github.com/chimmhuang/chimm.excel/issues/7)
* 新增支持 Sheet 页名称编辑 ([73dccc7](https://github.com/chimmhuang/chimm.excel/commit/73dccc76d87c618b2b095b9f32e99ba1762254f0))
* 新增超链接设置 ([652d5f9](https://github.com/chimmhuang/chimm.excel/commit/652d5f9fedb46233a2f1bc3e173eaa3b12f623aa)), closes [#I29Q35](https://github.com/chimmhuang/chimm.excel/issues/I29Q35)



# [](https://github.com/chimmhuang/chimm.excel/compare/v1.1.0...v) (2021-01-27)


### Bug Fixes

* 修复 Integer 类型写入表格不正确的问题 ([0b14afe](https://github.com/chimmhuang/chimm.excel/commit/0b14afe0fe26ed87842bbe84565377e43d5d747f)), closes [#9](https://github.com/chimmhuang/chimm.excel/issues/9)
* 修复公式获取错误的问题 ([2302192](https://github.com/chimmhuang/chimm.excel/commit/23021928df4bb63324be29b6de134443880ddde3)), closes [#I2M861](https://github.com/chimmhuang/chimm.excel/issues/I2M861)
* 修复列过多导致模板出错的问题 ([4cb56f8](https://github.com/chimmhuang/chimm.excel/commit/4cb56f860fdb3bad0d78ff9e83371b34d11d8ce6)), closes [#5](https://github.com/chimmhuang/chimm.excel/issues/5)
* 修复在删除/添加一行的时候，需要更新合并单元格的参数 ([e2aa5d9](https://github.com/chimmhuang/chimm.excel/commit/e2aa5d9834f63d6c69d6f6a1e90cf08b4fdbdd42))
* 修改包路径 ([e9818e6](https://github.com/chimmhuang/chimm.excel/commit/e9818e6939c3d0a994e867f3a8045774f7810ec4))
* 删除未使用到的包 ([4cc76e6](https://github.com/chimmhuang/chimm.excel/commit/4cc76e6b197e86beae8a98703b767c857d1d4f4c))
* 更改工具名称 ([28bc744](https://github.com/chimmhuang/chimm.excel/commit/28bc7446ad171a33f67df63add85ae6c88275bcc))
* 生成的 excel 单元格出现黑色背景 ([816650d](https://github.com/chimmhuang/chimm.excel/commit/816650d94d12176f08c81fd7cd916e7ce95ee570)), closes [#I29P3](https://github.com/chimmhuang/chimm.excel/issues/I29P3)
* 防喝茶 ([d792a1d](https://github.com/chimmhuang/chimm.excel/commit/d792a1dfde9d19d0f036c5a6c59bf1922400be35))
* 防喝茶 ([6621cdc](https://github.com/chimmhuang/chimm.excel/commit/6621cdc14ab2562c3b15809902cae6f1c2d23470))
* 防喝茶 ([189274c](https://github.com/chimmhuang/chimm.excel/commit/189274cf91d618fe52a8f10f21e1c65d3cd0bd04))


### Features

* antlr新增对公式的支持 ([42757d4](https://github.com/chimmhuang/chimm.excel/commit/42757d42ba7d6c807f23e50bd0e72092f7c591e2))
* update demo.xlsx ([2ce11b2](https://github.com/chimmhuang/chimm.excel/commit/2ce11b28829d572f8b8865ae90a67fe66bee5d1f))
* 单元格加粗新增指定列名范围加粗 ([b5c98aa](https://github.com/chimmhuang/chimm.excel/commit/b5c98aad6e23bf8116da10edf764c1359182e65d)), closes [#4](https://github.com/chimmhuang/chimm.excel/issues/4)
* 完善了表格遍历的功能 ([148c904](https://github.com/chimmhuang/chimm.excel/commit/148c904887780c3d6e51b98af63f0c1adfb7fdf7))
* 支持map格式 ([c352007](https://github.com/chimmhuang/chimm.excel/commit/c352007c568340e62493fe8ad91e0c1639c0fb2d)), closes [#I294Y2](https://github.com/chimmhuang/chimm.excel/issues/I294Y2)
* 支持公式 ([2cc4973](https://github.com/chimmhuang/chimm.excel/commit/2cc49736865c71acd5425fe21be9565a80754b1f))
* 支持带变量的公式，如：SUM(A1,A2,${demo.value}) ([be9c390](https://github.com/chimmhuang/chimm.excel/commit/be9c3900e31a5f737e4eebc36d4368c6bdcfb7c1))
* 新增一行支持自动更新公式 ([0d6bcea](https://github.com/chimmhuang/chimm.excel/commit/0d6bceaabd5083a254f03604569fe6d39b7c5312))
* 新增功能：添加行/删除行 ([9dfea54](https://github.com/chimmhuang/chimm.excel/commit/9dfea54e5a9540d3d219af199e49c81ab2ed7215))
* 新增动态添加/删除表格的测试 ([3ee1fac](https://github.com/chimmhuang/chimm.excel/commit/3ee1faccd228243579fc1b78a7a229f69ce15714))
* 新增单元格批量合并的功能 ([118583c](https://github.com/chimmhuang/chimm.excel/commit/118583c2c11a816a9d6027d617581bfb87250bae)), closes [#7](https://github.com/chimmhuang/chimm.excel/issues/7)
* 新增合并单元格的功能 ([9a92513](https://github.com/chimmhuang/chimm.excel/commit/9a92513129a00d19b1f61ddb3efd13e43f4ed00f))
* 新增支持 Sheet 页名称编辑 ([73dccc7](https://github.com/chimmhuang/chimm.excel/commit/73dccc76d87c618b2b095b9f32e99ba1762254f0))
* 新增更改单元格边框样式的功能 ([f60bcf8](https://github.com/chimmhuang/chimm.excel/commit/f60bcf861e4194244057a56da87e5df996e59161))
* 新增表格数据的填充 ([75d7fd8](https://github.com/chimmhuang/chimm.excel/commit/75d7fd8303d5b4e148dd47a3e35d44d0dd1e2e0b))
* 新增超链接设置 ([652d5f9](https://github.com/chimmhuang/chimm.excel/commit/652d5f9fedb46233a2f1bc3e173eaa3b12f623aa)), closes [#I29Q35](https://github.com/chimmhuang/chimm.excel/issues/I29Q35)
* 更新版本号为： 1.0.1 ([f5f4f14](https://github.com/chimmhuang/chimm.excel/commit/f5f4f145d480ff6a630c1717a8467b3dab99efc6))
* 获取 sheet 页，默认索引设置为 0 ([f62a76e](https://github.com/chimmhuang/chimm.excel/commit/f62a76ea42ccc5cc8cb15c2d76e475295a0276eb))



