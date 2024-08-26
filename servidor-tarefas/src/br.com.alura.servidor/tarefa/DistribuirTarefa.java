package br.com.alura.servidor.tarefa;

import br.com.alura.servidor.comando.ComandoC1;
import br.com.alura.servidor.comando.ComandoC2;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public record DistribuirTarefa(ExecutorService executorService, Socket socket, ServidorTarefa servidor) implements Runnable {

    @Override
    public void run() {
        System.out.println("Distribuindo Tarefas para " + socket.getPort());
        try {

            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String linha = entradaCliente.nextLine().toUpperCase();
                System.out.println("Comando recebido: " + linha);

                switch (linha) {
                    case "C1": {
                        saidaCliente.println("Tarefa C1 concluída com sucesso");
                        executorService.execute(new ComandoC1(saidaCliente));
                        break;
                    }
                    case "C2": {
                        saidaCliente.println("Tarefa C2 concluída com sucesso");
                        executorService.execute(new ComandoC2(saidaCliente));
                        break;
                    }
                    case "C3": {
                        saidaCliente.println("Tarefa C3 concluída com sucesso");
                        break;
                    }
                    case "FIM": {
                        saidaCliente.println("Desligando servidor");
                        servidor.finalizarServidor();
                        return;
                    }
                    default: {
                        saidaCliente.println("Tarefa nao encontrada");
                        break;
                    }
                }
            }

            saidaCliente.close();
            entradaCliente.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
