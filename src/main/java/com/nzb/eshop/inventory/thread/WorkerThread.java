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
		while (true) {
			break;
		}
		return true;
	}

}
