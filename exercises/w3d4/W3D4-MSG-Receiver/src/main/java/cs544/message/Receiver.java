package cs544.message;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver {
	
	@RabbitHandler
	public void receive(Person msg) {
		System.out.println("Received: " + msg.getName());
	}
}
