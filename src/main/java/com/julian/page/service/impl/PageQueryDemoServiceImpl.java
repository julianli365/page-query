package com.julian.page.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.julian.page.common.PageData;
import com.julian.page.common.PageInfo;
import com.julian.page.common.PageParam;
import com.julian.page.mapper.QueryMapper;
import com.julian.page.service.PageQueryDemoService;
import com.julian.page.vo.req.QueryReq;
import com.julian.page.vo.resp.QueryResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分页查询demo服务
 *
 * @author lijs
 * @since 2024年12月24日16:00:51
 */
@Slf4j
@Service
public class PageQueryDemoServiceImpl implements PageQueryDemoService {


    @Resource
    private QueryMapper mapper;


    @Override
    public PageData<QueryResp> queryPage(QueryReq req, PageParam pageParam) {
        Page<QueryResp> pageData = mapper.queryPage(req, PageInfo.of(pageParam));
        return PageData.of(pageData);
    }

}
