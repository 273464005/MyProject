package org.com.lyz.base.model.po;

import java.util.List;

public class XT_GNB {
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
}