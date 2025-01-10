package com.julian.page.vo.req;

import com.julian.page.common.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询入参
 *
 * @author lijs
 * @since 2024年12月24日15:55:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryReqExt extends PageParam {


    /**
     * 报告编码
     */
    private String reportNo;

}
