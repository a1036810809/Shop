package com.lzfmy.model;

import javax.persistence.*;

@Entity
public class Orderitem {
    private int itemid;
    private Integer count;
    private Double subtotal;
    private Product product;
    private Order order;
    private int oid;
    private int pid;

    @Id
    @Column(name = "itemid")
    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "oid")
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
    
    @Basic
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "subtotal")
    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @ManyToOne
    @JoinColumn(name = "pid")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oid")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderitem orderitem = (Orderitem) o;

        if (itemid != orderitem.itemid) return false;
        if (count != null ? !count.equals(orderitem.count) : orderitem.count != null) return false;
        if (subtotal != null ? !subtotal.equals(orderitem.subtotal) : orderitem.subtotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemid;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (subtotal != null ? subtotal.hashCode() : 0);
        return result;
    }
}
