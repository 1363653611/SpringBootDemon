package com.zbcn.combootfile;

import com.zbcn.combootfile.pub.config.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
public class ComBootFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComBootFileApplication.class, args);
    }

}
