package com.ejemplo.JDBCDavidChema.Entities;

import java.math.BigDecimal;
import java.sql.Blob;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"supplier_ids",
	"id",
	"product_code",
	"product_name",
	"description",
	"standard_cost",
	"list_price",
	"reorder_level",
	"target_level",
	"quantity_per_unit",
	"discontinued",
	"minimum_reorder_quantity",
	"category",
	"attachments"
})

public class Product {
	private String supplier_ids;
	private Integer id;
	private String product_code;
	private String product_name;
	private String description;
	private BigDecimal standard_cost;
	private BigDecimal list_price;
	private Integer reorder_level;
	private Integer target_level;
	private String quantity_per_unit;
	private Boolean discontinued;
	private Integer minimum_reorder_quantity;
	private String category;
	private Blob attachments;
	
	
	public Product() {
		
	}

	public Product(String supplier_ids, Integer id, String product_code, String product_name, String description,
			BigDecimal standard_cost, BigDecimal list_price, Integer reorder_level, Integer target_level,
			String quantity_per_unit, Boolean discontinued, Integer minimum_reorder_quantity, String category,
			Blob attachments) {
		
		this.supplier_ids = supplier_ids;
		this.id = id;
		this.product_code = product_code;
		this.product_name = product_name;
		this.description = description;
		this.standard_cost = standard_cost;
		this.list_price = list_price;
		this.reorder_level = reorder_level;
		this.target_level = target_level;
		this.quantity_per_unit = quantity_per_unit;
		this.discontinued = discontinued;
		this.minimum_reorder_quantity = minimum_reorder_quantity;
		this.category = category;
		this.attachments = attachments;
	}

	
	public String getSupplier_ids() {
		return supplier_ids;
	}

	public void setSupplier_ids(String supplier_ids) {
		this.supplier_ids = supplier_ids;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public BigDecimal getStandard_cost() {
		return standard_cost;
	}

	public void setStandard_cost(BigDecimal standard_cost) {
		this.standard_cost = standard_cost;
	}

	
	public BigDecimal getList_price() {
		return list_price;
	}

	public void setList_price(BigDecimal list_price) {
		this.list_price = list_price;
	}

	
	public Integer getReorder_level() {
		return reorder_level;
	}

	public void setReorder_level(Integer reorder_level) {
		this.reorder_level = reorder_level;
	}

	
	public Integer getTarget_level() {
		return target_level;
	}

	public void setTarget_level(Integer target_level) {
		this.target_level = target_level;
	}

	
	public String getQuantity_per_unit() {
		return quantity_per_unit;
	}

	public void setQuantity_per_unit(String quantity_per_unit) {
		this.quantity_per_unit = quantity_per_unit;
	}

	
	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	
	public Integer getMinimum_reorder_quantity() {
		return minimum_reorder_quantity;
	}

	public void setMinimum_reorder_quantity(Integer minimum_reorder_quantity) {
		this.minimum_reorder_quantity = minimum_reorder_quantity;
	}

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public Blob getAttachments() {
		return attachments;
	}

	public void setAttachments(Blob attachments) {
		this.attachments = attachments;
	}

	
	@Override
	public String toString() {
		return "Product [" + (supplier_ids != null ? "supplier_ids=" + supplier_ids + ", " : "")
				+ (id != null ? "id=" + id + ", " : "")
				+ (product_code != null ? "product_code=" + product_code + ", " : "")
				+ (product_name != null ? "product_name=" + product_name + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (standard_cost != null ? "standard_cost=" + standard_cost + ", " : "")
				+ (list_price != null ? "list_price=" + list_price + ", " : "")
				+ (reorder_level != null ? "reorder_level=" + reorder_level + ", " : "")
				+ (target_level != null ? "target_level=" + target_level + ", " : "")
				+ (quantity_per_unit != null ? "quantity_per_unit=" + quantity_per_unit + ", " : "")
				+ (discontinued != null ? "discontinued=" + discontinued + ", " : "")
				+ (minimum_reorder_quantity != null ? "minimum_reorder_quantity=" + minimum_reorder_quantity + ", "
						: "")
				+ (category != null ? "category=" + category + ", " : "")
				+ (attachments != null ? "attachments=" + attachments : "") + "]";
	}
	
	
}
