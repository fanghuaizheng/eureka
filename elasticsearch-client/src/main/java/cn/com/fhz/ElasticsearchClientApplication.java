package cn.com.fhz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@EnableWebSecurity
public class ElasticsearchClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchClientApplication.class, args);
	}
}
