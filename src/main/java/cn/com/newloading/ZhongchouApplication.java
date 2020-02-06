package cn.com.newloading;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@ServletComponentScan
@MapperScan("cn.com.newloading.dao")
public class ZhongchouApplication extends SpringBootServletInitializer {

	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		// 注意这里要指向原先用main方法执行的Application启动类
//		return builder.sources(ZhongchouApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(ZhongchouApplication.class, args);
	}

}
