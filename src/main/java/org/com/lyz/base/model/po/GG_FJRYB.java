package org.com.lyz.base.model.po;

import org.com.lyz.base.model.basepo.BasePo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GG_FJRYB extends BasePo{

    public static final RowMapper<GG_FJRYB> ROW_MAPPER = new BeanPropertyRowMapper<GG_FJRYB>(GG_FJRYB.class);

    public GG_FJRYB(){

    }

    public String getPkValue() {
        return this.id;
    }

    public void setPkValue(String id) {
        this.id = id;
    }

    public GG_FJRYB(String id){
        this.id = id;
    }

    private String id;

    private String fjid;

    private String ryid;

    private Integer zt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid == null ? null : fjid.trim();
    }

    public String getRyid() {
        return ryid;
    }

    public void setRyid(String ryid) {
        this.ryid = ryid == null ? null : ryid.trim();
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    /**
     * 将resultset一行转换为PO
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public GG_FJRYB mapRow(ResultSet resultSet, int i) throws SQLException {
        return ROW_MAPPER.mapRow(resultSet,i);
    }
}