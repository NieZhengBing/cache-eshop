package com.nzb.eshop.inventory.service;

import com.nzb.eshop.inventory.model.ProductInventory;

/**
 * @Description: 商品库存Service接口
 * @author M
 *
 */
public interface ProductInventoryService {

	/**
	 * 更新商品库存
	 * 
	 * @param productInventory
	 */
	void updateProductInventory(ProductInventory productInventory);

	/**
	 * 删除Redis中的商品库存的缓存
	 * 
	 * @param productInventory
	 *            商品库存
	 */
	void removeProductInventoryCache(ProductInventory productInventory);

	/**
	 * 根据商品id查询商品库存
	 * 
	 * @param productId
	 *            商品id
	 * @return 商品库存
	 */
	ProductInventory findProductInventory(Integer productId);

	/**
	 * 设置商品库存的缓存
	 * 
	 * @param productInventory
	 *            商品库存
	 */
	void setProductInventoryCache(ProductInventory productInventory);

	/**
	 * 获取商品库存的缓存
	 * 
	 * @param productId
	 *            商品id
	 * @return 商品库存
	 */
	ProductInventory getProductInventoryCache(Integer productId);

}
