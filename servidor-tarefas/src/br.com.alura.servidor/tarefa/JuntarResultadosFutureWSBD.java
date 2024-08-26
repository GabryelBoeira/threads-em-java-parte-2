package br.com.alura.servidor.tarefa;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public record JuntarResultadosFutureWSBD(Future<String> futureWS, Future<String> futureBD,
                                         PrintStream saidaCliente) implements Callable<Void> {

    @Override
    public Void call() throws Exception {
        System.out.println("Aguardando resultados de WS e BD");

        try {
            String resultadoWS = futureWS.get(15, TimeUnit.SECONDS);
            String resultadoBD = futureBD.get(15, TimeUnit.SECONDS);

            this.saidaCliente.println("Resultados obtidos C2: " + resultadoWS + " e " + resultadoBD);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Cancelando execução do WS e BD");
            this.saidaCliente.println("Timeout excedido na execução do WS e BD");
            futureWS.cancel(true);
            futureBD.cancel(true);
        }

        System.out.println("Finalizado JuntarResultadosFutureWSBD");
        return null;
    }
}
