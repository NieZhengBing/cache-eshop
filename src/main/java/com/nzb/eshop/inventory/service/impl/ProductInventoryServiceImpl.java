package com.nzb.eshop.inventory.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nzb.eshop.inventory.dao.RedisDao;
import com.nzb.eshop.inventory.mapper.ProductInventoryMapper;
import com.nzb.eshop.inventory.model.ProductInventory;
import com.nzb.eshop.inventory.service.ProductInventoryService;

/**
 * @Description: 商品库存Service实现类
 * @author M
 *
 */
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Resource
	private ProductInventoryMapper productInventoryMapper;

	@Resource
	private RedisDao redisDao;

	@Override
	public void updateProductInventory(ProductInventory productInventory) {
		productInventoryMapper.updateProductInventory(productInventory);
		System.out.println("===============日志============: 已修改数据库中的库存， 商品id=" + productInventory.getProductId()
				+ ", 商品库存数量=" + productInventory.getInventoryCnt());
	}

	@Override
	public void removeProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		System.out.println("===============日志============: 删除redis中的缓存, key=" + key);
		redisDao.delete(key);
	}

	@Override
	public ProductInventory findProductInventory(Integer productId) {
		return productInventoryMapper.findProductInventory(productId);
	}

	@Override
	public void setProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		redisDao.set(key, String.valueOf(productInventory.getInventoryCnt()));
		System.out.println("===========日志1==============: 已更新商品库存的缓存， 商品id=" + productInventory.getProductId()
				+ ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + key);
	}

	@Override
	public ProductInventory getProductInventoryCache(Integer productId) {
		Long inventoryCnt = 0L;
		String key = "product:inventory:" + productId;
		String result = redisDao.get(key);
		if (result != null && !"".equals(result)) {
			try {
				inventoryCnt = Long.valueOf(result);
				return new ProductInventory(productId, inventoryCnt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
