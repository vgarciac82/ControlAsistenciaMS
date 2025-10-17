package com.axtel.interfaces.controlAsistencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.axtel.interfaces.controlAsistencia")
public class ControlAsistenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlAsistenciaApplication.class, args);
	}

}
