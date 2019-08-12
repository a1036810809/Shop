package com.lzfmy.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Category {
    private int cid;
    private String cname;
    private List<Categorysecond> categorySeconds = new LinkedList<>();

    @Id
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @OrderBy("csid")
    public List<Categorysecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(List<Categorysecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (cid != category.cid) return false;
        if (cname != null ? !cname.equals(category.cname) : category.cname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        return result;
    }
}
