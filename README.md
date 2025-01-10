# page-query

#### 一、介绍
>基于mybatisPlus的分页查询优化。<br><br>优化点：支持自定义排序、支持配置是否查询总记录数、输出结构简化。

#### 二、软件架构
>底层依赖了mybatisPlus，需要配置MybatisPlusInterceptor以实现SQL拦截和分页<br><br>参考：PageQueryApplication.mybatisPlusInterceptor

#### 三、安装教程

>Copy ./src/main/com/julian/page/common 下4个Page前缀的class到你的本地项目下即可<br>（如缺少依赖请参考build.gradle）
><br><br>提示：如果你想启动测试本项目，请先更新bootstrap.yaml数据源配置

#### 四、使用说明

1. Controller的处理

> 入参处理：<br>
>  方式一：参数列表增加参数PageParam<br>
>  方式二：查询的参数对象继承分页参数PageParam<br><br>
> 出参处理：<br>
> 使用 PageData 封装<br><br>
> 参考：com.julian.page.controller.PageQueryDemoController.queryList

2. Service的处理 

> a. 使用 PageInfo.of(pageParam) 将分页参数转换为mybatis分页参数PageInfo;<br>
> b. 使用 PageData.of(list) 将结果集转换为新分页结果集PageData<br><br>
> 参考：com.julian.page.service.impl.PageQueryDemoServiceImpl.queryPage

3. Mapper的处理

> sql中返回的字段（如record_no）如果需要作为排序字段，必须定义别名（驼峰结构，recordNo），跟resp对象中字段定义保持一致<br><br>
> 原因：前端传的排序字段是resp中的字段定义，如果不定义别名，会报错提示字段不存在<br>
> 参考：com.julian.page.mapper.QueryMapper.queryPage

4. 请求示例 
  
> 例：查第1页，每页5条，查询总记录数，排序按 id 升序按 recordNo 降序<br>
> curl --location --request GET "http://localhost:8001/demo/query/list?pageNumber=1&pageSize=5&searchCount=1&sort=id,asc,recordNo,desc"<br>
> <br>效果同：<br>
> curl --location --request GET "http://localhost:8001/demo/query/list?pageNumber=1&pageSize=5&sort=id,recordNo,desc"<br><br>
> 提示：CMD窗口执行curl命令，或在浏览器地址栏粘贴http地址测试

6. 结果集示例
> data：业务数据集合<br>
> page：分页信息，总记录数6，总页数2，当前指定页1，当前每页条数5
```
{
    "data": [
      {
        "id": 1,
        "recordNo": "RN001"
      },
      {
        "id": 2,
        "recordNo": "RN003"
      },
      {
        "id": 2,
        "recordNo": "RN002"
      },
      {
        "id": 4,
        "recordNo": "RN004"
      },
      {
        "id": 5,
        "recordNo": "RN005"
      }
    ],
    "page": {
      "pageNumber": 1,
      "pageSize": 5,
      "dataCount": 6,
      "pageCount": 2
    }
  }
```

6. 参数说明
- 入参（参考com.julian.page.common.PageParam）

|     字段名     | 类型 | 是否必填 | 默认值 |    含义    | 说明                                                                                                                   | 
|:----:|:----:|:----:|:----:|:----:|:---------------------------------------------------------------------------------------------------------------------|
| pageNumber  | int | 否 | 1 |   指定页码    | 从1开始（传0会兼容处理为1）                                                                                                      | 
|  pageSize   | int | 否 | 10 |   每页条数   |                                                                                                                      | 
| searchCount | boolean | 否 | TRUE | 是否查询总记录数 | 建议不需要总数时指定（当false时不会查询总记录数，即pageCount和dataCount返回为0）                                                                 | 
|    sort     | Array&lt;String> | 否 | - |   排序字段   | 字段和升降序都占一个元素，param参数中该字段用逗号','分隔。<br/>默认升序排列<br/>如：id,asc,recordNo,desc 表示优先按id升序,再按recordNo降序（效果同 id,recordNo,desc） | 

- 出参（参考com.julian.page.common.PageData）

| 字段名 | 类型 | 是否必填 | 默认值 |  含义  | 说明                              | 
|:----:|:----:|:----:|:----:|:----:|:--------------------------------|
| data |Array&lt;Object>|  是   | - | 业务数据 |
| page |Object|  是   | - | 分页参数 |
| page.pageNumber | int |  是   | - | 指定页码 |
| page.pageSize | int |  是   | - | 每页条数 |
| page.dataCount | int |  是   | 0 | 总记录数 | searchCount为false时，不查询总记录数，此时为0 | 
| page.pageCount | int |  是   | 0 | 总页数  | searchCount为false时，不查询总页数，此时为0  | 

#### 五、参与贡献

1.  Fork 本仓库
2.  新建 feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
