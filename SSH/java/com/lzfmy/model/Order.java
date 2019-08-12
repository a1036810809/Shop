package com.lzfmy.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "orders", schema = "shop", catalog = "")
public class Order {
    private int oid;
    private Double total;
    private Timestamp ordertime;
    private Integer state;
    private String name;
    private String phone;
    private String addr;
    private User user;
    private int uid;
    private List<Orderitem> orderitems = new LinkedList<>();

    @Id
    @Column(name = "oid")
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "total")
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "ordertime")
    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @OrderBy("itemid")
    public List<Orderitem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<Orderitem> orderitems) {
        this.orderitems = orderitems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (oid != order.oid) return false;
        if (total != null ? !total.equals(order.total) : order.total != null) return false;
        if (ordertime != null ? !ordertime.equals(order.ordertime) : order.ordertime != null) return false;
        if (state != null ? !state.equals(order.state) : order.state != null) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (phone != null ? !phone.equals(order.phone) : order.phone != null) return false;
        if (addr != null ? !addr.equals(order.addr) : order.addr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (ordertime != null ? ordertime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        return result;
    }
}
