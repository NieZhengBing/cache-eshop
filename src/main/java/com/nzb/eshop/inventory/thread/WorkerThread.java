package com.nzb.eshop.inventory.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

import com.nzb.eshop.inventory.request.Request;

/**
 * @Description: 执行请求的工作线程
 * @author M
 *
 */
public class WorkerThread implements Callable<Boolean> {

	/**
	 * @description: 自己监控的内存队列
	 */
	private ArrayBlockingQueue<Request> queue;

	public WorkerThread(ArrayBlockingQueue<Request> queue) {
		this.queue = queue;
	}

	@Override
	public Boolean call() throws Exception {
		try {
			while (true) {
				// ArrayBlockingQueue, Blocking说明，如果队列满了，或者是空的，那么在执行的时候，会阻塞
				Request request = queue.take();
				System.out.println("===========日志===========: 工作线程处理请求, 商品id=" + request.getProductId());
				// 执行request
				request.process();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
