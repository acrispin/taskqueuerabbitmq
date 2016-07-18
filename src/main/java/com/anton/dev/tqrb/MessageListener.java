/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.tqrb;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Receptor
 * https://www.adictosaltrabajo.com/tutoriales/implementacion-de-emisor-y-receptor-basicos-con-rabbitmq/
 * @author anton
 */
public class MessageListener {
    private final static String QUEUE_NAME = "MAIN_QUEUE";
 
    public static void main(String[] args) throws IOException,
        TimeoutException, ShutdownSignalException,
        ConsumerCancelledException, InterruptedException {

      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      System.out.println(" [*] A la espera de mensajes. Para salir pulse: CTRL+C");
      QueueingConsumer consumer = new QueueingConsumer(channel);
      channel.basicConsume(QUEUE_NAME, true, consumer);

      while (true) {
        System.out.println("Obteniendo siguiente mensaje.");
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        String message = new String(delivery.getBody());
        System.out.println(" [x] Recibido: '" + message + "'");
        doWork(message);
        System.out.println(" [x] Hecho!!! ");
      }
    }

    private static void doWork(String task) throws InterruptedException {
      Thread.sleep(4000);
    }
}
