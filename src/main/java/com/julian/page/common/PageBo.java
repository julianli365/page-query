package com.julian.page.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页返回参数
 *
 * @author lijs
 * @since 2024年12月25日09:50:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBo {
    /**
     * 当前页
     */
    private Integer pageNumber;
    /**
     * 当前一页条数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long dataCount;
    /**
     * 总页数
     */
    private Integer pageCount;
}
