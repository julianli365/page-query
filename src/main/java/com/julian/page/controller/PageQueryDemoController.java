package com.julian.page.controller;


import com.julian.page.common.PageData;
import com.julian.page.common.PageParam;
import com.julian.page.common.Result;
import com.julian.page.service.PageQueryDemoService;
import com.julian.page.vo.req.QueryReq;
import com.julian.page.vo.req.QueryReqExt;
import com.julian.page.vo.resp.QueryResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 分页查询控制层
 *
 * @author lijs
 * @since 2024-12-24 15:06:48
 */
@RestController
@RequestMapping("/demo")
public class PageQueryDemoController {

    @Resource
    private PageQueryDemoService service;


    /**
     * 方式一：参数列表中放分页参数pageParam
     * 方式二：入参对象QueryReq继承分页参数pageParam
     */
    @GetMapping("/query/list")
    public Result<PageData<QueryResp>> queryList(QueryReq req, PageParam pageParam) {
        return Result.success(service.queryPage(req, pageParam));
    }

}

