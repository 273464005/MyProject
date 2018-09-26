package org.com.lyz.util.pageutil;

/**
 * 分页工具
 * @author： 鲁玉震
 * @time： 2018/6/26
 */
public class SplitPageInfo {

    private int page = 0;

    private int limit = 10;

    /**
     * limit中的起始位置（pageMin）
     * @return
     */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page == 1) {
            page = 0;
        }
        this.page = page;
    }

    /**
     * limit中的结束位置（pageMax）
     * @return
     */
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
