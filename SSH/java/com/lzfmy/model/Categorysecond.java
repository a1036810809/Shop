package com.lzfmy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Categorysecond {
    private int csid;
    private String csname;
    private int cid;
    
    private Category category;
    private Set<Product> products = new HashSet<>();
    @Id
    @Column(name = "csid")
    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    @Basic
    @Column(name = "csname")
    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    @Basic
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @ManyToOne
    @JoinColumn(name = "cid")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }
    @OneToMany(mappedBy = "categorySecond",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categorysecond that = (Categorysecond) o;

        if (csid != that.csid) return false;
        if (csname != null ? !csname.equals(that.csname) : that.csname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = csid;
        result = 31 * result + (csname != null ? csname.hashCode() : 0);
        return result;
    }
}
