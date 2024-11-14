package cs544.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {
	@Autowired
	private RabbitTemplate template;
	
	@Override
	public void run(String... args) throws Exception {
		String queue = "hello";
		//String msg = "Hello World!";
		Person msg = new Person("Frank");
		template.convertAndSend(queue, msg);
		System.out.println("Sent: " + msg +" to: " + queue);
	}
}
