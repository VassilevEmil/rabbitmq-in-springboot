package via.sdj3.rabbitmqinspringboot.controller;

import org.springframework.web.bind.annotation.*;
import via.sdj3.rabbitmqinspringboot.model.Product;
import via.sdj3.rabbitmqinspringboot.service.RabbitMQProductProducer;

@RestController
@RequestMapping(value = "/sdj3-rabbitmq/")
public class ProductController {
    private RabbitMQProductProducer rabbitMQProductProducer;

    public ProductController(RabbitMQProductProducer rabbitMQProductProducer) {
        this.rabbitMQProductProducer = rabbitMQProductProducer;
    }

    @GetMapping(value = "/products")
    public String sendProductDetails(@RequestParam("productName")String productName, @RequestParam("productId")String productId ){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        rabbitMQProductProducer.send(product);

        if (rabbitMQProductProducer.send(product)){
            System.out.println(product.getProductName() + "Sent to RabbitMQ Broker");
            return "Successfully sent to RabbitMQ";
        }else{
            return "Error: MEssage NOT sent";
        }
    }
}
