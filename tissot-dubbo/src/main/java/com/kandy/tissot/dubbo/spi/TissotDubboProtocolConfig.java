package com.kandy.tissot.dubbo.spi;

import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TissotDubboProtocolConfig {

	@Value("${dubbo.protocol.dubbo.port:20880}")
	private int port;

	@Value("${dubbo.protocol.dubbo.threads:10}")
	private int threads;

	// 服务提供者协议配置
	@Bean(name = "dubboProtocol")
	public ProtocolConfig getProtocolConfig() {
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(port);
		protocol.setThreads(threads);
		return protocol;
	}

}
