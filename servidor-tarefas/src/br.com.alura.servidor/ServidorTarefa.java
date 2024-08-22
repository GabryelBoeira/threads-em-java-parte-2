package br.com.alura.servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefa {

    public static void main(String[] args) throws Exception {
        System.out.println("---Iniciando o servidor ---");
        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Aceitando nova conexão na porta: "+ socket.getPort());

            Thread tarefa = new Thread(new DistribuirTarefa(socket));
            tarefa.start();
        }
    }
}