package com.kh.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {
	
	/* Auto Configuration (자동 구성)
	 * 
	 * Spring Boot의 핵심 기능
	 * Application의 ClassPath에 존재하는 라이브러리 및 설정을 기반으로 Bean을 자동으로 구성
	 * 
	 * @EnableAutoConfiguration
	 * Spring Boot의 자동 구성을 활성화해주는 애노테이션
	 * @SpringBootApplication 내부에 포함되어 있기 때문에 직접 작성할 일은 잘 없음
	 * 
	 * 
	 * 동작 순서
	 * 
	 * Application 시작 -> @SpringBootApplication 애노테이션이 붙은 클래스의 main 메서드가 호출됨
	 * Auto Configuration 활성화 -> @EnableAutoConfiguration을 통해 자동 구성을 활성화
	 * 
	 * @EnableAutoConfiguration
	 * @ComponentScan
	 * @Configuration
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
