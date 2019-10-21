package com.zbcn.bootbase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:config/cons-injec.xml") //导入xml配置项
public class SoundSystemConfig {
}
