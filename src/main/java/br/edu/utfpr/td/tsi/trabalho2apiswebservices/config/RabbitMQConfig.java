package br.edu.utfpr.td.tsi.trabalho2apiswebservices.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "transacoes.financeiras";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

}
