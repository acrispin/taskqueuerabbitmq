/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anton.dev.tqrb;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Emisor
 * https://www.adictosaltrabajo.com/tutoriales/implementacion-de-emisor-y-receptor-basicos-con-rabbitmq/
 * @author anton
 */
public class MessageProducer {
    private final static String QUEUE_NAME = "MAIN_QUEUE";
  
  public static void main(String[] args) throws IOException, TimeoutException {
 
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "¡Hola!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Enviar '" + message + "'");
    channel.close();
    connection.close();
  }
}
