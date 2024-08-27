package br.com.alura.servidor.tarefa;

import java.util.concurrent.BlockingQueue;

public record TarefaConsumir(BlockingQueue<String> fila) implements Runnable {

    @Override
    public void run() {
        try {
            String comando = null;

            while ((comando = this.fila.take()) != null) {
                System.out.println("Consumido o comando: " + comando + " na thread: " + Thread.currentThread().getName());
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
