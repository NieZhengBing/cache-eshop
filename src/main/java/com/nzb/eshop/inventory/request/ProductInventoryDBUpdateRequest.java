package com.nzb.eshop.inventory.request;

import com.nzb.eshop.inventory.model.ProductInventory;
import com.nzb.eshop.inventory.service.ProductInventoryService;

/**
 * @Description: 商品发生了交易，那么需要修改对应的库存 此时就会发送请求过来，要求修改库存,那么这个可能就是所谓的data uppdate
 *               request,数据更新请求
 * 
 * 
 *               cache aside pattern
 * 
 *               (1) 删除缓存 (2) 更新数据库
 * @author M
 *
 */
public class ProductInventoryDBUpdateRequest implements Request {

	private ProductInventory productInventory;

	private ProductInventoryService productInventoryService;

	public ProductInventoryDBUpdateRequest(ProductInventory productInventory,
			ProductInventoryService productInventoryService) {
		super();
		this.productInventory = productInventory;
		this.productInventoryService = productInventoryService;
	}

	@Override
	public void process() {
		System.out.println("===============日志============: 数据库更新请求开始执行, 商品id=" + productInventory.getProductId()
				+ ", 商品库存数量=" + productInventory.getInventoryCnt());
		// 删除redis缓存
		productInventoryService.removeProductInventoryCache(productInventory);

		// 为了模拟先删除了redis
		// 修改数据库中的库存中的缓存，然后还没更新数据库的时候，读请求过来了，这里可以人工sleep下
		/*
		 * try { Thread.sleep(30000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		productInventoryService.updateProductInventory(productInventory);
	}

	/**
	 * 获取商品id
	 */
	@Override
	public Integer getProductId() {
		return productInventory.getProductId();
	}

}
