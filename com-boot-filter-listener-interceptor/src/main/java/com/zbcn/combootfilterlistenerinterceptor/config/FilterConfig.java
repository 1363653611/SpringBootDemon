package com.zbcn.combootfilterlistenerinterceptor.config;

import com.zbcn.combootfilterlistenerinterceptor.Filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);

		List<String> urlList = new ArrayList<>();
		urlList.add("/*");

		filterRegistrationBean.setUrlPatterns(urlList);
		return filterRegistrationBean;
	}
}
