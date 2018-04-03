package com.nzb.eshop.inventory.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nzb.eshop.inventory.request.Request;
import com.nzb.eshop.inventory.request.RequestQueue;

/**
 * @Description: 请求处理线程池 单例
 * @author M
 *
 */
public class RequestProcessorThreadPool {

	/**
	 * @Description: 在实际项目中，线程池的大小，每个线程监控的内存队列的大小 都可以做到一个外部的配置文件中， 在这里我们简化，直接写死
	 */
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	/**
	 * @description: 内存队列
	 */
	// private List<ArrayBlockingQueue<Request>> queues = new
	// ArrayList<ArrayBlockingQueue<Request>>();

	public RequestProcessorThreadPool() {
		RequestQueue requestQueue = RequestQueue.getInstance();
		for (int i = 0; i < 10; i++) {
			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
			requestQueue.addQueue(queue);
			threadPool.submit(new WorkerThread(queue));
		}
	}

	/**
	 * @Description: 静态内部类的方式，去初始化单例，单例有很多种，这里静态内部类方式是绝对线程安全的
	 * @author M
	 *
	 */
	private static class Singleton {
		private static RequestProcessorThreadPool instance;

		static {
			instance = new RequestProcessorThreadPool();
		}

		public static RequestProcessorThreadPool getInstance() {
			return instance;
		}

	}

	/**
	 * @description: jvm的机制去保证多线程并发安全 内部类的初始化，一定只会发生一次,不管多少个线程并发去初始化
	 * @return
	 */
	public static RequestProcessorThreadPool getInstance() {
		return Singleton.getInstance();
	}

	/**
	 * @description: 初始化的便捷方法
	 */
	public void init() {
		getInstance();
	}

}
