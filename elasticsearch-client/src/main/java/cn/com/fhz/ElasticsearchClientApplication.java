package cn.com.fhz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ElasticsearchClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchClientApplication.class, args);
	}
}
