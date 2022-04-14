package via.sdj3.rabbitmqinspringboot.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import via.sdj3.rabbitmqinspringboot.model.Product;

@Service
public class RabbitMQProductProducer {
    private RabbitTemplate rabbitTemplate;
    private String Exchange = "x.sdj3..product.exchange";
    private String RoutingKey = "product.routingKey";

    public RabbitMQProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean send(Product product){
        try{
            rabbitTemplate.convertAndSend(Exchange, RoutingKey, product);
            //logging
            System.out.println("Sent message ==> " + product);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

}
