package com.desafio.taxajuros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TaxajurosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxajurosApplication.class, args);
	}

}
