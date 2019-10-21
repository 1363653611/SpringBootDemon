package com.zbcn.bootbase.config;

import com.zbcn.bootbase.dao.CompactDisc;
import com.zbcn.bootbase.dao.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDConfig {
	@Bean   // 将SgtPeppers注册为 SpringContext中的bean
	public CompactDisc compactDisc() {
		return new SgtPeppers();  // CompactDisc类型的
	}

}
