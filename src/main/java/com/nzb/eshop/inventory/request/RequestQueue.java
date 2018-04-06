package com.nzb.eshop.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description: 请求的内存队列
 * @author M
 *
 */
public class RequestQueue {
	/**
	 * @description: 内存队列
	 */
	private List<ArrayBlockingQueue<Request>> queues = new ArrayList<ArrayBlockingQueue<Request>>();

	private static class Singleton {
		private static RequestQueue instance;

		static {
			instance = new RequestQueue();
		}

		public static RequestQueue getInstance() {
			return instance;
		}
	}

	public static RequestQueue getInstance() {
		return Singleton.getInstance();
	}

	public static RequestQueue init() {
		return getInstance();
	}

	/**
	 * @description: 添加到内存队列
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<Request> queue) {
		queues.add(queue);
	}

	/**
	 * 获取内存队列的数量
	 * 
	 * @return
	 */
	public int queueSize() {
		return queues.size();
	}

	/**
	 * 获取内存队列
	 * 
	 * @param index
	 * @return
	 */
	public ArrayBlockingQueue<Request> getQueue(int index) {
		return queues.get(index);
	}

}
