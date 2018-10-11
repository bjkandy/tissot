package com.kandy.tissot.dubbo.spi;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.kandy.tissot.dubbo.DubboConfigBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
class TissotDubboConfigBuilderImpl implements DubboConfigBuilder {

	@Autowired
	private ApplicationConfig application;

	@Resource(name = "dubboProtocol")
	private ProtocolConfig protocol;

	@Resource(name = "defaultRegistry")
	private RegistryConfig registry;

	@Value(value = "${dubbo.reference.timeout:30000}")
	private int timeout;

	@Value(value = "${dubbo.reference.check:false}")
	private boolean check;

	@Value(value = "${dubbo.reference.retries:0}")
	private int retries;

	public <T> ReferenceConfig<T> build(Class<T> cls, String version) {
		ReferenceConfig<T> reference = new ReferenceConfig<T>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface(cls);
		reference.setVersion(version);
		reference.setTimeout(this.timeout);
		reference.setCheck(this.check);
		reference.setRetries(this.retries);
		return reference;
	}

}
