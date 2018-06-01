package org.com.lyz.base.model.basepo;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;

/**
 * 作者： 鲁玉震
 * 创建时间： 2018/5/31
 */
public abstract class BasePo<T extends BasePo> implements RowMapper<T>, Serializable, Cloneable{

    public BasePo() {

    }

    public abstract String getPkValue();

    public abstract void setPkValue(String id);
}
