package br.edu.utfpr.td.tsi.trabalho2apiswebservices;

import br.edu.utfpr.td.tsi.trabalho2apiswebservices.producer.TransacaoProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Trabalho2apiswebservicesApplication {

	@Autowired
	private TransacaoProducer transacaoProducer;

	public static void main(String[] args) {
		SpringApplication.run(Trabalho2apiswebservicesApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Inicia o produtor de transações após a aplicação ser iniciada
		transacaoProducer.lerCsv();
	}
}
