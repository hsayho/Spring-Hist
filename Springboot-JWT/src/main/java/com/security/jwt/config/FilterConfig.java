package com.security.jwt.config;

import com.security.jwt.filter.MyFilter1;
import com.security.jwt.filter.MyFilter2;
import com.security.jwt.filter.MyFilter3;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

/**
 * 여기세 등록된 Filter들은 Security Filter들이 모두 실행되고 나서 우선순위에 맞게 실행됨
 * */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호가 우선순위가 가장 높음
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1); // 낮은 번호가 우선순위가 가장 높음
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter3> filter3() {
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>(new MyFilter3());
        bean.addUrlPatterns("/*");
        bean.setOrder(2); // 낮은 번호가 우선순위가 가장 높음
        return bean;
    }
}
