package com.lzfmy.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Product {
    private Integer pid;

    private String pname;

    private Double marketPrice;

    private Double shopPrice;

    private String image;

    private String pdesc;

    private Integer isHot;

    private Date pdate;

    private Integer csid;

    private Categorysecond categorysecond;
    
    private MultipartFile upload;

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }

    public Categorysecond getCategorysecond() {
        return categorysecond;
    }

    public void setCategorysecond(Categorysecond categorySecond) {
        this.categorysecond = categorySecond;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc == null ? null : pdesc.trim();
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }
}