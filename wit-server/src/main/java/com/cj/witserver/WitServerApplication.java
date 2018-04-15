package com.cj.witserver;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan({"com.cj.*.mapper"})
@ComponentScan(basePackages = {"com.cj"})
//@EnableWebMvc
public class WitServerApplication extends WebMvcConfigurerAdapter {

	private static Logger logger = Logger.getLogger(WitServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WitServerApplication.class, args);
	}

	//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//		//1.需要定义一个convert转换消息的对象;
//		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//		//2.添加 fastJson 的配置信息,比如：是否要格式化返回的json数据;
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//		//3处理中文乱码问题
//		List<MediaType> fastMediaTypes = new ArrayList<>();
//		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//		//4.在convert中添加配置信息.
//		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
//		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//		//5.将convert添加到converters当中.
//		converters.add(fastJsonHttpMessageConverter);
//	}

}
