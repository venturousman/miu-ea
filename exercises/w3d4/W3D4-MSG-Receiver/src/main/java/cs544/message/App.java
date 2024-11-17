package cs544.message;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
	@Bean
	public Queue hello() {
		return new Queue("hello");
	}

	// @Bean
	// public Queue book() {
	// return new Queue("book");
	// }

	// without this config you get security errors when receiving messages
	@Bean
	public SimpleMessageConverter converter() {
		SimpleMessageConverter converter = new SimpleMessageConverter();
		converter.setAllowedListPatterns(List.of("cs544.*", "java.util.*"));
		return converter;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
