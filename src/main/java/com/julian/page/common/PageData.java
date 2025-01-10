package com.julian.page.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * 分页结果集
 *
 * @author lijs
 * @since 2024年12月25日09:57:00
 */
@Data
public class PageData<T> {

    /**
     * 业务数据
     */
    private List<T> data;
    /**
     * 分页参数
     */
    private PageBo page;

    public PageData() {
    }

    public PageData(List<T> data, PageBo page) {
        this.data = data;
        this.page = page;
    }

    public PageData(IPage<T> pageRecords) {
        if (Objects.nonNull(pageRecords)) {
            page = new PageBo((int) pageRecords.getCurrent(), (int) pageRecords.getSize(), pageRecords.getTotal(), (int) pageRecords.getPages());
            this.data = pageRecords.getRecords();
        }
    }

    public static <T> PageData<T> of(IPage<T> pageRecords) {
        return new PageData<>(pageRecords);
    }

}
