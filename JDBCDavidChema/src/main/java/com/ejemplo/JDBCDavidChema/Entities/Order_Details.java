package com.ejemplo.JDBCDavidChema.Entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.json.bind.annotation.JsonbPropertyOrder;
	
@JsonbPropertyOrder({
		"id",
		"order_id",
		"product_id",
		"quantity",
		"unit_price",
		"discount",
		"status_id",
		"date_allocated",
		"purchase_order_id",
		"inventory_id"
	})
	
public class Order_Details{
	
	private Integer id;
	private Integer order_id;
	private Integer product_id;
	private BigDecimal quantity;
	private BigDecimal unit_price;
	private Double discount;
	private Integer status_id;
	private Date date_allocated;
	private Integer purchase_order_id;
	private Integer inventory_id;
	
	
	
	public Order_Details() {
		
	}

	public Order_Details(Integer id, Integer order_id, Integer product_id, BigDecimal quantity, BigDecimal unit_price,
			Double discount, Integer status_id, Date date_allocated, Integer purchase_order_id, Integer inventory_id) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.discount = discount;
		this.status_id = status_id;
		this.date_allocated = date_allocated;
		this.purchase_order_id = purchase_order_id;
		this.inventory_id = inventory_id;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	
	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	
	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	
	public Date getDate_allocated() {
		return date_allocated;
	}

	public void setDate_allocated(Date date_allocated) {
		this.date_allocated = date_allocated;
	}

	
	public Integer getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(Integer purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	
	public Integer getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
	}

	
	@Override
	public String toString() {
		return "Order_Details [" + (id != null ? "id=" + id + ", " : "")
				+ (order_id != null ? "order_id=" + order_id + ", " : "")
				+ (product_id != null ? "product_id=" + product_id + ", " : "")
				+ (quantity != null ? "quantity=" + quantity + ", " : "")
				+ (unit_price != null ? "unit_price=" + unit_price + ", " : "")
				+ (discount != null ? "discount=" + discount + ", " : "")
				+ (status_id != null ? "status_id=" + status_id + ", " : "")
				+ (date_allocated != null ? "date_allocated=" + date_allocated + ", " : "")
				+ (purchase_order_id != null ? "purchase_order_id=" + purchase_order_id + ", " : "")
				+ (inventory_id != null ? "inventory_id=" + inventory_id : "") + "]";
	}
	
}
	
