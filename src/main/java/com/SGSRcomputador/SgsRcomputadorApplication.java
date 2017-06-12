package com.SGSRcomputador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.SGSRcomputador.controller.HomeController;


@SpringBootApplication
@ComponentScan(basePackageClasses={HomeController.class}, basePackages="com.SGSRcomputador.*")
public class SgsRcomputadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgsRcomputadorApplication.class, args);
	}
}
