package com.kandy.tissot.dubbo;

import com.alibaba.dubbo.config.ReferenceConfig;

public interface DubboConfigBuilder {

	public <T> ReferenceConfig<T> build(Class<T> cls, String version);
}