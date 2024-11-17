package cs544.message;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	@Bean
	public Queue hello() {
		return new Queue("hello");
	}

	@Bean
	public Queue book() {
		return new Queue("book");
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
