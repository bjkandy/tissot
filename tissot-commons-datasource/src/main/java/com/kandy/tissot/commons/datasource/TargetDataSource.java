package com.kandy.tissot.commons.datasource;

import java.lang.annotation.*;

/**
 * 指定数据源标签
 *
 * 在方法上使用，用于指定使用哪个数据源
 *
 * Created by zhaoxinguo on 2016年12月5日
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

	String value();
}
