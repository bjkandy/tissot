package com.kandy.tissot.dubbo.spi;

import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TissotDubboRegistryConfig {

	@Value("${dubbo.registry.address}")
	private String registryAddress;

	@Value("${dubbo.registry.client:zkclient}")
	private String registryClient;

	// 连接注册中心配置
	@Bean(name = "defaultRegistry")
	public RegistryConfig getRegistryConfig() {
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress(this.registryAddress);
		registry.setCheck(false);
		registry.setClient(registryClient);
		return registry;
	}
}
