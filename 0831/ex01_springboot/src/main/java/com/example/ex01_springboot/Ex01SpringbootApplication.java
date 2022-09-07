package com.example.ex01_springboot;

import com.example.ex01_springboot.components.A;
import com.example.ex01_springboot.components.B;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	// configuration 포함
public class Ex01SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex01SpringbootApplication.class, args);
	}

	public A a() {
		return new A();
	}
}
