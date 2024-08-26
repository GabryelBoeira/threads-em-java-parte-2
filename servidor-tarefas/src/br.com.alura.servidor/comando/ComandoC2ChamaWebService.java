package br.com.alura.servidor.comando;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public record ComandoC2ChamaWebService(PrintStream saida) implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Executando comando C2 - Chama Web-Service");

        saida.println("Processando C2 - Chama Web-Service");
        Thread.sleep(10000);
        Integer numero = new Random().nextInt(100) + 1;

        saida.println("Comando C2 Finalizado - Web-Service");

        return numero.toString();
    }
}
