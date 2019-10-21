package com.zbcn.bootbase.config;

import com.zbcn.bootbase.dao.CDPlayer;
import com.zbcn.bootbase.dao.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import 使用
 */
@Configuration
@Import(CDConfig.class)  //导入CDConfig的配置
public class CDPlayerConfig {

	@Bean(name = "cDPlayer")
	public CDPlayer cdPlayer(CompactDisc compactDisc) {
        /*这里会注入CompactDisc类型的bean
         这里注入的这个bean是CDConfig.class中的CompactDisc类型的那个bean*/
		return new CDPlayer(compactDisc);
	}
}
