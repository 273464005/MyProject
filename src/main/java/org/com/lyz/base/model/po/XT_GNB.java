package org.com.lyz.base.model.po;

import org.com.lyz.base.model.basepo.BasePo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class XT_GNB extends BasePo<XT_GNB>{

    private static final RowMapper<XT_GNB> ROW_MAPPER = new BeanPropertyRowMapper<XT_GNB>(XT_GNB.class);

    public XT_GNB(){

    }

    public XT_GNB(String id){
        this.id = id;
    }

    private String id;

    private String gnmc;

    private Integer dyqx;

    private String fid;

    private String ljdz;

    private Integer gnlb;

    private Integer sxh;

    private Integer zt;

    private List<XT_GNB> childrenGn;

    public void setChildrenGn(List<XT_GNB> childrenGn) {
        this.childrenGn = childrenGn;
    }

    public List<XT_GNB> getChildrenGn() {
        return childrenGn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc == null ? null : gnmc.trim();
    }

    public Integer getDyqx() {
        return dyqx;
    }

    public void setDyqx(Integer dyqx) {
        this.dyqx = dyqx;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getLjdz() {
        return ljdz;
    }

    public void setLjdz(String ljdz) {
        this.ljdz = ljdz == null ? null : ljdz.trim();
    }

    public Integer getGnlb() {
        return gnlb;
    }

    public void setGnlb(Integer gnlb) {
        this.gnlb = gnlb;
    }

    public Integer getSxh() {
        return sxh;
    }

    public void setSxh(Integer sxh) {
        this.sxh = sxh;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    /**
     * 将ResultSet一行转换为PO
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public XT_GNB mapRow(ResultSet resultSet, int i) throws SQLException {
        return ROW_MAPPER.mapRow(resultSet,i);
    }

    public String getPkValue() {
        return this.id;
    }

    public void setPkValue(String id) {
        this.id = id;
    }
}