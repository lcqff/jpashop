package jpabook.jpashop;

import static jpabook.jpashop.Message.PRINT_INCOME_RATE_MESSAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JpashopApplication.class, args);

		System.out.printf(PRINT_INCOME_RATE_MESSAGE.getMessage().formatted(99.8));
	}

}
