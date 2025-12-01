package br.edu.utfpr.td.tsi.trabalho2apiswebservices.producer;

import br.edu.utfpr.td.tsi.trabalho2apiswebservices.config.RabbitMQConfig;
import br.edu.utfpr.td.tsi.trabalho2apiswebservices.entities.Transacao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class TransacaoProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${csv.file.path}")
    private String csvFile;

    public void lerCsv() {

        String line;
        String cvsSplitBy = ",";

        System.out.println("Iniciando leitura do arquivo CSV...");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Pular o cabeçalho se necessário
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Remove aspas que podem vir no CSV do Excel
                line = line.replace("\"", "");
                String[] dados = line.split(cvsSplitBy);



                Transacao transacao = new Transacao(
                        Long.parseLong(dados[0]), // codigo
                        dados[1],                 // cedente
                        dados[2],                 // pagador
                        Double.parseDouble(dados[3]), // valor
                        dados[4]                  // vencimento
                );

                // Envia para a fila "transacoes.financeiras"
                rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, transacao);
                System.out.println("Enviado: " + transacao.getCodigo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
