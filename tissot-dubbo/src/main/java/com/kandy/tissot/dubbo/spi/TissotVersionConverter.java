package com.kandy.tissot.dubbo.spi;//package com.ufenqi.dubbo.spi;
//
//import org.springframework.stereotype.Component;
//
///**
// * 因为版本不兼容,暂时没有使用这个类
// * @author ruanxiaozhen
// *
// */
//@Component
//class VersionConverter {
//
//	public String convert(String version) {
//		if (version == null) {
//			throw new IllegalArgumentException("版本号不能为空");
//		}
//		String main = null;
//		String suffix = null;
//		if (version.contains("-")) {
//			String[] strs = version.split("-");
//			if (strs.length > 2) {
//				throw new IllegalArgumentException("版本号格式错误:" + version);
//			}
//			main = strs[0];
//			suffix = strs[1];
//		} else {
//			main = version;
//		}
//		String[] strs = main.split("\\.");
//		if (strs.length < 2) {
//			throw new IllegalArgumentException("版本号格式错误:" + version);
//		}
//		if (suffix == null) {
//			return strs[0] + "." + strs[1];
//		} else {
//			return strs[0] + "." + strs[1] + "-" + suffix;
//		}
//	}
//
//}
