package br.com.alura.servidor.tarefa;

import br.com.alura.servidor.comando.ComandoC1;
import br.com.alura.servidor.comando.ComandoC2;
import br.com.alura.servidor.comando.ComandoC2ChamaBancoDeDados;
import br.com.alura.servidor.comando.ComandoC2ChamaWebService;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
                        saidaCliente.println("Chamando Tarefa C1");
                        executorService.execute(new ComandoC1(saidaCliente));
                        break;
                    }
                    case "C2": {
                        saidaCliente.println("Chamando Tarefa C2");
                        Future<String> futureWS = executorService.submit(new ComandoC2ChamaWebService(saidaCliente));
                        Future<String> futureBD = executorService.submit(new ComandoC2ChamaBancoDeDados(saidaCliente));

                        this.executorService.submit(new JuntarResultadosFutureWSBD(futureWS, futureBD, saidaCliente));
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
