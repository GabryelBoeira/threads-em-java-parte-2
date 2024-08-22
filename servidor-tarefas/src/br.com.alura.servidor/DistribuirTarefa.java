package br.com.alura.servidor;

import java.net.Socket;

public record DistribuirTarefa(Socket socket) implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Distribuindo Tarefas para " + socket.getPort());
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
