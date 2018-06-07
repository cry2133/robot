package com.robot;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.robot.*.dao")
@SpringBootApplication
public class RobotApplication extends SpringBootServletInitializer{
	public static Logger log=Logger.getLogger(RobotApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
        log.info("******************robot启动成功******************** \n" +
        		" _____   ____  ____   ____ _______  \n"+
				"|  __ \\ / __ \\|  _ \\ / __ \\__   __| \n"+
				"| |__) | |  | | |_) | |  | | | |    \n"+
				"|  _  /| |  | |  _ <| |  | | | |    \n"+
				"| | \\ \\| |__| | |_) | |__| | | |    \n"+
				"|_|  \\_\\\\____/|____/ \\____/  |_| \n");
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RobotApplication.class);
    }

    @Bean
    public LocaleResolver localeResolver() {
    	CookieLocaleResolver slr = new CookieLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(3600);//设置cookie有效期.
        return slr;
    }
}
