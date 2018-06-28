package org.com.lyz.util.pageutil;

/**
 * 分页工具
 * @author： 鲁玉震
 * @time： 2018/6/26
 */
public class SplitPageInfo {

    private int page = 0;

    private int limit = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page == 1) {
            page = 0;
        }
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
