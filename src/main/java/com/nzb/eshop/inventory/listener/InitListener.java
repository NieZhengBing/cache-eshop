package com.nzb.eshop.inventory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nzb.eshop.inventory.thread.RequestProcessorThreadPool;

/**
 * @Description: 系统初始化监听器
 * @author M
 *
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 初始化工作线程和内存队列
		RequestProcessorThreadPool requestProcessorThreadPool = RequestProcessorThreadPool.getInstance();
		requestProcessorThreadPool.init();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
