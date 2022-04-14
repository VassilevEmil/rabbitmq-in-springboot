package via.sdj3.rabbitmqinspringboot.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean // easy way to pass around objects
    public Exchange productExhange(){
        return new DirectExchange("x.sdj3..product.exchange");
    }

    @Bean
    public Queue productQueue(){
        return new Queue("q.sdj3.product.queue");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(productQueue()).to(productExhange()).with("product.routingKey").noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();

    }
}
