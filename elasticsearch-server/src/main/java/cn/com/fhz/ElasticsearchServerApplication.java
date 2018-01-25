package cn.com.fhz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ElasticsearchServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchServerApplication.class, args);
	}
}
