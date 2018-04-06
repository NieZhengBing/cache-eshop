package com.nzb.eshop.inventory.mapper;

import org.apache.ibatis.annotations.Param;

import com.nzb.eshop.inventory.model.ProductInventory;

/**
 * @Description: 库存数量Mapper
 * @author M
 *
 */
public interface ProductInventoryMapper {

	/**
	 * 更新库存数量
	 * 
	 * @param inventoryCnt
	 *            商品库存
	 */
	void updateProductInventory(ProductInventory Productinventory);

	/**
	 * 根据商品库存id查询商品库存信息
	 * 
	 * @param productId
	 *            商品id
	 * @return 商品库存信息
	 */
	ProductInventory findProductInventory(@Param("productId") Integer productId);
}
