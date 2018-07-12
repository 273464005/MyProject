package org.com.lyz.base.model.po;

import org.com.lyz.base.model.basepo.BasePo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IMG_LOG extends BasePo{

    private static final RowMapper<IMG_LOG> ROW_MAPPER = new BeanPropertyRowMapper<IMG_LOG>(IMG_LOG.class);

    public IMG_LOG(){

    }

    public IMG_LOG(String id){
        this.id = id;
    }

    private String id;

    private String glid;

    private String imgid;

    private Long logsj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid == null ? null : glid.trim();
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid == null ? null : imgid.trim();
    }

    public Long getLogsj() {
        return logsj;
    }

    public void setLogsj(Long logsj) {
        this.logsj = logsj;
    }

    public String getPkValue() {
        return this.id;
    }

    public void setPkValue(String id) {
        this.id = id;
    }

    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return ROW_MAPPER.mapRow(resultSet,i);
    }
}