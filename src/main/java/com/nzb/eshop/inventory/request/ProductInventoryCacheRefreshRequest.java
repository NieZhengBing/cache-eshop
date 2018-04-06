package com.nzb.eshop.inventory.request;

import com.nzb.eshop.inventory.model.ProductInventory;
import com.nzb.eshop.inventory.service.ProductInventoryService;

/**
 * @Description: 重新加载商品库存缓存
 * @author M
 *
 */
public class ProductInventoryCacheRefreshRequest implements Request {

	/**
	 * 商品id
	 */
	private Integer productId;

	/**
	 * 库存service
	 */
	private ProductInventoryService productInventoryService;

	public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService) {
		super();
		this.productId = productId;
		this.productInventoryService = productInventoryService;
	}

	@Override
	public void process() {
		// 从数据库中查询最新的商品库存信息
		ProductInventory productInventory = productInventoryService.findProductInventory(productId);
		System.out.println("===========日志1==============: 已查询商品最新的库存数量， 商品id=" + productId + ", 商品库存数量="
				+ productInventory.getInventoryCnt());
		// 将最新的商品库存信息,刷新到redis缓存中区
		productInventoryService.setProductInventoryCache(productInventory);
	}

	/**
	 * 获取商品id
	 */
	@Override
	public Integer getProductId() {
		return this.productId;
	}

}
