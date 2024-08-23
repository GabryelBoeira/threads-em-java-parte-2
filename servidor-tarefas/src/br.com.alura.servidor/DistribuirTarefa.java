package br.com.alura.servidor;

import java.net.Socket;
import java.util.Scanner;

public record DistribuirTarefa(Socket socket) implements Runnable {

    @Override
    public void run() {
        System.out.println("Distribuindo Tarefas para " + socket.getPort());
        try {

            Scanner entrada = new Scanner(socket.getInputStream());
            while (entrada.hasNextLine()) {
                String linha = entrada.nextLine();
                System.out.println("Tarefa: " + linha);
            }

            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
