package com.kandy.tissot.dubbo.spi;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration()
class TissotDubboApplicationConfig {

	@Value("${dubbo.application.name}")
	private String name;

	@Value("${dubbo.application.version}")
	private String version;

	@Value("${dubbo.application.owner:undefined}")
	private String owner;

	@Resource(name = "defaultMonitor")
	private MonitorConfig monitor;

	@Value(value = "${dubbo.application.simple-monitor:false}")
	private boolean simpleMonitor;

	@Bean
	public ApplicationConfig getApplicationConfig() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName(name);
		application.setVersion(version);
		application.setOwner(owner);
		if (simpleMonitor) {
			application.setMonitor(monitor);
		}
		return application;
	}

	// 简单的监控中心
	@Bean(name = "defaultMonitor")
	public MonitorConfig getMonitorConfig() {
		MonitorConfig monitor = new MonitorConfig();
		monitor.setProtocol("registry");
		return monitor;
	}
}
