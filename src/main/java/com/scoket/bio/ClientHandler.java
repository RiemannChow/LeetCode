package com.scoket.bio;

import java.io.IOException;

import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final RequestHandler requestHandler;

    public ClientHandler(Socket clientSocket, RequestHandler requestHandler) {
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
    }
    @Override
    public void run() {
        try (Scanner input = new Scanner(clientSocket.getInputStream())) {
            //针对每个请求能够进行无限的交互操作
            while (true) {
                //真正阻塞的原因在这边，一直要等待客户端的输入信息，线程被耽搁的点
                String request = input.nextLine();
                if ("quit".equals(request)) {
                    break;
                }
                System.out.println(request);
                String response = requestHandler.handler(request);
                clientSocket.getOutputStream().write(response.getBytes());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
