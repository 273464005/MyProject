package org.com.lyz.base.model.po;

import org.com.lyz.base.model.basepo.BasePo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GG_IMGS extends BasePo<GG_IMGS>{

    public static final RowMapper<GG_IMGS> ROW_MAPPER = new BeanPropertyRowMapper<GG_IMGS>(GG_IMGS.class);

    public GG_IMGS(){

    }

    public GG_IMGS(String id){
        this.id = id;
    }

    private String id;

    private String tpmc;

    private String tpdz;

    private Long scsj;

    private Integer height;

    private Integer width;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTpmc() {
        return tpmc;
    }

    public void setTpmc(String tpmc) {
        this.tpmc = tpmc == null ? null : tpmc.trim();
    }

    public String getTpdz() {
        return tpdz;
    }

    public void setTpdz(String tpdz) {
        this.tpdz = tpdz == null ? null : tpdz.trim();
    }

    public Long getScsj() {
        return scsj;
    }

    public void setScsj(Long scsj) {
        this.scsj = scsj;
    }

    public String getPkValue() {
        return this.id;
    }

    public void setPkValue(String id) {
        this.id = id;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public GG_IMGS mapRow(ResultSet resultSet, int i) throws SQLException {
        return ROW_MAPPER.mapRow(resultSet,i);
    }
}