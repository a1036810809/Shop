package cn.techaction.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ActionProduct {
	private Integer id;
	private String name;
	private Integer productId;
	private Integer partsId;
	private String iconUrl;
	private String subImages;
	private String detail;
	private String specParam;
	private BigDecimal price;
	private Integer stock;
	private Integer status; //上下架
	private Integer hot;
	private Date created;
	private Date updated;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getPartsId() {
		return partsId;
	}
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getSubImages() {
		return subImages;
	}
	public void setSubImages(String subImages) {
		this.subImages = subImages;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getSpecParam() {
		return specParam;
	}
	public void setSpecParam(String specParam) {
		this.specParam = specParam;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date date) {
		this.updated = date;
	}
	
}
