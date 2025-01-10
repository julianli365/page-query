package com.julian.page.common;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页请求参数
 *
 * @author lijs
 * @since 2024年12月25日10:12:19
 */
@Data
public class PageParam implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 当前页,可不传,默认1
     */
    private int pageNumber = 1;
    /**
     * 一页条数,可不传,默认10
     */
    private int pageSize = 10;
    /**
     * 是否查询总记录数,可不传,默认true
     * 建议按需指定(为false不会查询总记录数,即pageCount和dataCount返回为0)
     */
    private boolean searchCount = true;
    /**
     * 排序字段,可为空
     * 字段和升降序都占一位,用英文逗号,分隔
     * 排序默认升序(即升序时可不指定)
     * 如id,asc,no,desc 表示同时按id升序,按no降序(效果同id,no,desc)
     */
    private String[] sort;

    public PageParam() {
    }

    public PageParam(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageParam(int pageNumber, int pageSize, boolean searchCount) {
        this(pageNumber, pageSize);
        this.searchCount = searchCount;
    }

    public PageParam(int pageNumber, int pageSize, String... sort) {
        this(pageNumber, pageSize);
        this.sort = sort;
    }

    public PageParam(int pageNumber, int pageSize, boolean searchCount, String... sort) {
        this(pageNumber, pageSize, searchCount);
        this.sort = sort;
    }

    public List<OrderItem> getSorts() {

        if (sort == null || sort.length == 0) {
            return new ArrayList<>();
        }

        List<OrderItem> orders = new ArrayList<>();
        for (int i = 0; i < sort.length; i++) {
            String s = sort[i];
            if (i == 0 && (hasSort(s))) {
                continue;
            }
            if (sort.length == 1) {
                OrderItem orderItem = new OrderItem(s, Boolean.TRUE);
                orders.add(orderItem);
                break;
            }
            if (i != sort.length - 1 && !hasSort(s) && hasSort(sort[i + 1])) {
                OrderItem orderItem = new OrderItem(s, isAsc(sort[i + 1]));
                orders.add(orderItem);
                continue;
            }
            if (!hasSort(s)) {
                OrderItem orderItem = new OrderItem(s, Boolean.TRUE);
                orders.add(orderItem);
            }
        }

        return orders;
    }

    private boolean isAsc(String str) {
        return str.equalsIgnoreCase(SqlKeyword.ASC.name());
    }

    private boolean isDesc(String str) {
        return str.equalsIgnoreCase(SqlKeyword.DESC.name());
    }

    private boolean hasSort(String str) {
        return isAsc(str) || isDesc(str);
    }
}