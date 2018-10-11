package com.kandy.tissot.commons.datasource;

import com.kandy.tissot.core.utils.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * 动态数据源上下文
 *
 * Created by zhaoxinguo on 2016年12月5日
 */
public class DynamicDataSourceContextHolder {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
	
	/**
	 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
	 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
	 */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	/**
	 * 使用setDataSourceType设置当前数据源
	 * 
	 * @param dataSourceType
	 */
	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return contextHolder.get();
	}

	/**
	 * 移除当前数据源
	 */
	public static void clearDataSourceType() {
		contextHolder.remove();
	}

	/**
	 * 判断指定DataSrouce当前是否存在
	 *
	 * @param dataSourceId
	 * @return
	 */
	public static boolean containsDataSource(String dataSourceId) {
//		return dataSourceIds.contains(dataSourceId);
		return (null != ApplicationUtil.getBean(dataSourceId) && ApplicationUtil.getBean(dataSourceId) instanceof DataSource) ? Boolean.TRUE:Boolean.FALSE;
	}

}
