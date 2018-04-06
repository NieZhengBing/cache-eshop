package com.nzb.eshop.inventory.service;

import com.nzb.eshop.inventory.request.Request;

/**
 * @Description: 请求异步执行的service
 * @author M
 *
 */
public interface RequestAsyncProcessService {

	void process(Request request);

}
