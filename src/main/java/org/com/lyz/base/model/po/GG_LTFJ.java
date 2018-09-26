package org.com.lyz.base.model.po;

import org.com.lyz.base.model.basepo.BasePo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GG_LTFJ extends BasePo{

    public static final RowMapper<GG_LTFJ> ROW_MAPPER = new BeanPropertyRowMapper<GG_LTFJ>(GG_LTFJ.class);

    public GG_LTFJ(){

    }

    public GG_LTFJ(String id){
        this.id = id;
    }

    private String id;

    private String fjh;

    private String fjmc;

    private String fjms;

    private Integer fjzt;

    private String fjmm;

    private Long cjsj;

    private String cjr;

    private String cjrmc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh == null ? null : fjh.trim();
    }

    public String getFjmc() {
        return fjmc;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc == null ? null : fjmc.trim();
    }

    public String getFjms() {
        return fjms;
    }

    public void setFjms(String fjms) {
        this.fjms = fjms == null ? null : fjms.trim();
    }

    public Integer getFjzt() {
        return fjzt;
    }

    public void setFjzt(Integer fjzt) {
        this.fjzt = fjzt;
    }

    public String getFjmm() {
        return fjmm;
    }

    public void setFjmm(String fjmm) {
        this.fjmm = fjmm == null ? null : fjmm.trim();
    }

    public String getPkValue() {
        return this.id;
    }

    public void setPkValue(String id) {
        this.id = id;
    }

    public Long getCjsj() {
        return cjsj;
    }

    public void setCjsj(Long cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }

    public String getCjrmc() {
        return cjrmc;
    }

    public void setCjrmc(String cjrmc) {
        this.cjrmc = cjrmc == null ? null : cjrmc.trim();
    }

    /**
     * 将resultset一行转换为PO
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public GG_LTFJ mapRow(ResultSet resultSet, int i) throws SQLException {
        return ROW_MAPPER.mapRow(resultSet,i);
    }
}