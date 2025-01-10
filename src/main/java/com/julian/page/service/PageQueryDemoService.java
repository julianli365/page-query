package com.julian.page.service;


import com.julian.page.common.PageData;
import com.julian.page.common.PageParam;
import com.julian.page.vo.req.QueryReq;
import com.julian.page.vo.req.QueryReqExt;
import com.julian.page.vo.resp.QueryResp;

/**
 * 分页查询demo服务
 *
 * @author lijs
 * @since 2024年12月24日15:48:10
 */
public interface PageQueryDemoService {


    /**
     * 查询列表数据
     */
    PageData<QueryResp> queryPage(QueryReq req, PageParam pageParam);

}
