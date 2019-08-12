package cn.techaction.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ActionOrderItemVo {
	private Long orderNo;
	private Integer goodsId;
	private String goodsName;
	private String iconUrl;
	private	BigDecimal curPrice;
	private Integer quantity;
	private BigDecimal totalPrice;
	private Date created;
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public BigDecimal getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(BigDecimal curPrice) {
		this.curPrice = curPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
