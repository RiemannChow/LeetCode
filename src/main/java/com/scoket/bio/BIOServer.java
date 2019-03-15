package com.scoket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        RequestHandler requestHandler = new RequestHandler();
        try(ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BIOServer has started,listenning on port: "+serverSocket.getLocalSocketAddress());
            //多个客户端的请求连接
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection from" + clientSocket.getRemoteSocketAddress());
                //为了给线程池可以去进行任务的执行
                executor.submit(new ClientHandler(clientSocket,requestHandler));
            }
        }
    }
}
