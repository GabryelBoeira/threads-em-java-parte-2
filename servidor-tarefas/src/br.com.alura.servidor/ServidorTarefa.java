package br.com.alura.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefa {

    public static void main(String[] args) throws Exception {
        System.out.println("---Iniciando o servidor ---");
        ServerSocket serverSocket = new ServerSocket(5000);

        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();


        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Aceitando nova conexão na porta: "+ socket.getPort());

            executorService.execute(new DistribuirTarefa(socket));
        }
    }
}