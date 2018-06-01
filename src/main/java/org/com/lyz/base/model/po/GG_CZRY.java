package org.com.lyz.base.model.po;

import org.com.lyz.base.model.basepo.BasePo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GG_CZRY extends BasePo<GG_CZRY>{

    public static final RowMapper<GG_CZRY> ROW_MAPPER = new BeanPropertyRowMapper<GG_CZRY>(GG_CZRY.class);

    private String id;

    private String mc;

    private String mm;

    private Integer xb;

    private Integer nl;

    private String sjh;

    private String dlh;

    private Integer qx;

    private Integer zt;

    private String email;

    private String sfzh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm == null ? null : mm.trim();
    }

    public Integer getXb() {
        return xb;
    }

    public void setXb(Integer xb) {
        this.xb = xb;
    }

    public Integer getNl() {
        return nl;
    }

    public void setNl(Integer nl) {
        this.nl = nl;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh == null ? null : sjh.trim();
    }

    public String getDlh() {
        return dlh;
    }

    public void setDlh(String dlh) {
        this.dlh = dlh == null ? null : dlh.trim();
    }

    public Integer getQx() {
        return qx;
    }

    public void setQx(Integer qx) {
        this.qx = qx;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    /**
     * 将resultset一行转换为PO
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public GG_CZRY mapRow(ResultSet resultSet, int i) throws SQLException {
        return ROW_MAPPER.mapRow(resultSet,i);
    }

    public String getPkValue() {
        return this.id;
    }

    public void setPkValue(String id) {
        this.id = id;
    }
}