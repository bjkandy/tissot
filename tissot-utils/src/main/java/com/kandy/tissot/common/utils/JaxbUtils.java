package com.kandy.tissot.common.utils;

import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Jaxb2工具类
 * 
 * @author bjkandy
 * @create 2013-3-29 下午2:40:14
 */
public class JaxbUtils {

    /**
     * JavaBean转换成xml 默认编码UTF-8
     * 
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        return convertToXml(obj, "UTF-8");
    }

    /**
     * JavaBean转换成xml
     * 
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();

            // 命名空间前缀(携程酒店业务使用,忽略)
//            marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
//                @Override
//                public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
//                    if (namespaceUri.equals("http://www.opentravel.org/OTA/2003/05")) return "ns";
//                   return suggestion;
//                }
//            });
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * xml转换成JavaBean
     * 
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        if(null == xml || StringUtils.isEmpty(xml)){
            return null;
        }
        
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    
    /**
     * xml转换成JavaBean
     * 
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c,Class<?> o) {
        if(null == xml || StringUtils.isEmpty(xml)){
            return null;
        }
        
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c,o);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
