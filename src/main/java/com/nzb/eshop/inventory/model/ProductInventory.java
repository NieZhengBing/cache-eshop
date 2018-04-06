package com.nzb.eshop.inventory.model;

/**
 * @Description: 库存model
 * @author M
 *
 */
public class ProductInventory {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 库存数量
	 */
	private Long inventoryCnt;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Long getInventoryCnt() {
		return inventoryCnt;
	}

	public void setInventoryCnt(Long inventoryCnt) {
		this.inventoryCnt = inventoryCnt;
	}

	public ProductInventory(Integer productId, Long inventoryCnt) {
		super();
		this.productId = productId;
		this.inventoryCnt = inventoryCnt;
	}

	public ProductInventory() {
		super();
	}

}
