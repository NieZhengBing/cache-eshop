package com.nzb.eshop.inventory.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nzb.eshop.inventory.model.ProductInventory;
import com.nzb.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.nzb.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.nzb.eshop.inventory.request.Request;
import com.nzb.eshop.inventory.service.ProductInventoryService;
import com.nzb.eshop.inventory.service.RequestAsyncProcessService;
import com.nzb.eshop.inventory.vo.Response;

/**
 * @Description: 商品库存的controller
 * @author M
 *
 *         模拟的场景: (1) 一个更新商品库存的请求，先删除redis中的缓存，然后模拟卡顿5s,
 *         (2)在卡顿的5s内,我们发送一个商品缓存的读请求，因为此时redis中没有缓存，就会来请求将数据中的最新数据刷新到缓存中
 *         (3)此时读请求会路由到同一个内存队列中，阻塞住，不会执行 (4)等5s后，写请求完成数据库的更新之后，读请求才会执行
 *         (5)读请求执行的时候，会将在最新的库存从数据库中查询出来，然后更新到缓存中.
 * 
 *         如果是不一致的情况，可能会出现说redis中还是库存为100, 但是数据库也许已经更新为99了
 *         现在做了一致性保障之后，就可以保证说，数据是一致的.
 */
@Controller
public class ProductInventoryController {

	@Resource
	private ProductInventoryService productInventoryService;

	@Resource
	private RequestAsyncProcessService requestAsyncProcessService;

	/**
	 * 更新商品库存
	 * 
	 * @param productInventory
	 * @return
	 */
	@RequestMapping("/updateProductInventory")
	@ResponseBody
	public Response updateProductInventory(ProductInventory productInventory) {
		System.out.println("==============日志==============: 接收到更新商品库存的请求, 商品id=" + productInventory.getProductId()
				+ ", 商品库存数量=" + productInventory.getInventoryCnt());
		Response response = null;
		try {
			Request request = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
			requestAsyncProcessService.process(request);
			response = new Response(Response.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response(Response.FAILURE);
		}
		return response;

	}

	@RequestMapping("/getProductInventory")
	@ResponseBody
	public ProductInventory getProductInventory(Integer productId) {
		System.out.println("============日志==============: 接收到一个商品库存的读请求，商品id=" + productId);
		ProductInventory productInventory = null;
		try {
			Request request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService);
			requestAsyncProcessService.process(request);
			// 将请求扔给serrvice异步处理后，就需要while(ture)一会儿，在这里hang住，去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作,将最新的数据刷新到缓存中
			long startTime = System.currentTimeMillis();
			long endTime = 0L;
			long waitTime = 0L;
			// 等待超过200ms没有从缓存中获取到结果
			while (true) {
				/*
				 * if (waitTime > 40000) { break; }
				 */
				// 一般公司里面，面向用户的读请求控制在200ms，就可以了
				if (waitTime > 200) {
					break;
				}

				// 尝试去redis中读取一次商品库存的缓存数据
				productInventory = productInventoryService.getProductInventoryCache(productId);
				// 如果读取到了结果，就直接返回
				if (productInventory != null) {
					return productInventory;
				}
				// 如果没有读取到,那么等待一段时间
				else {
					Thread.sleep(30);
					endTime = System.currentTimeMillis();
					waitTime = endTime - startTime;
				}
			}
			// 直接尝试从数据库中读取数据
			productInventory = productInventoryService.findProductInventory(productId);
			if (productInventory != null) {
				System.out.println("===========日志1==============: 在200ms内读取到了redis中的1库存缓存， 商品id="
						+ productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
				return productInventory;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ProductInventory(productId, -1L);
	}

}
