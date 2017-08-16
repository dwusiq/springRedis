package com.wusiq.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置装载springMVC上下文
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    /**
     * 可以用非Web的组件来充实完善RootConfig。在RootConfig里边，我们可以去定义一些跟Spring Web模块关系不是太紧密的Bean，就比
     * 如DataSource或者是HibernateTemplate这种与数据库相关的Bean。
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 可以去定义我们需要的bean,就比如ViewResolver跟MultipartResolver。
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class};//指定配置类
    }

    /**
     * 配置拦截的路径
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.do","*.json"};
    }
}
