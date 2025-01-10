package com.julian.page.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 分页执行参数
 *
 * @author lijs
 * @since 2024年12月25日10:09:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageInfo extends Page implements Serializable {

    private static final long serialVersionUID = -1L;

    public PageInfo() {
        super();
    }

    public PageInfo(PageParam pageParam) {
        this(pageParam, false);
    }

    public PageInfo(PageParam pageParam, boolean beginFromZero) {
        super(pageParam.getPageNumber() + (beginFromZero ? 1 : 0), pageParam.getPageSize(), pageParam.isSearchCount());
        addOrder(pageParam.getSorts());
    }

    public static PageInfo of(PageParam pageParam) {
        return new PageInfo(pageParam);
    }

    public static PageInfo createFrom(Object from) {
        PageInfo pageInfo = new PageInfo();
        return pageInfo.copyFrom(from);
    }

    public PageInfo copyFrom(Object from) {
        BeanUtils.copyProperties(from, this);
        return this;
    }

    public static PageBuilder builder() {
        return new PageBuilder();
    }

    public static class PageBuilder {
        private PageParam pageParam;

        PageBuilder() {
        }

        public PageBuilder pageParams(PageParam pageParam) {
            this.pageParam = pageParam;
            return this;
        }

        public PageBuilder pagePlus() {
            if (null != this.pageParam) {
                this.pageParam.setPageNumber(this.pageParam.getPageNumber() + 1);
            }
            return this;
        }

        public PageInfo build() {
            return new PageInfo(this.pageParam);
        }

    }
}
