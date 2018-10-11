package com.kandy.tissot.dubbo;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
/**
 * 简单的服务导出,配置了一些常用的默认参数,如果有很复杂的配置请使用dubbo提供的xml
 *
 */
public @interface DubboService {

}
