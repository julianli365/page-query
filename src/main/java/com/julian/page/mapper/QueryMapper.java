package com.julian.page.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.julian.page.common.PageInfo;
import com.julian.page.vo.req.QueryReq;
import com.julian.page.vo.resp.QueryResp;

/**
 * QueryMapper
 *
 * @author lijs
 * @since 2024-12-24 15:06:49
 */
public interface QueryMapper {

    Page<QueryResp> queryPage(QueryReq req, PageInfo pageInfo);
}

