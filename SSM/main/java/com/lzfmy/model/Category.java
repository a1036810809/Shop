package com.lzfmy.model;

import java.util.List;

public class Category {
    private Integer cid;

    private String cname;
    
    private List<Categorysecond> categoryseconds;

    public List<Categorysecond> getCategoryseconds() {
        return categoryseconds;
    }

    public void setCategoryseconds(List<Categorysecond> categoryseconds) {
        this.categoryseconds = categoryseconds;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}