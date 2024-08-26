package br.com.alura.servidor.comando;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public record ComandoC2ChamaBancoDeDados(PrintStream saida) implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Executando comando C2 - Chama Banco de Dados");

        saida.println("Processando C2 - Chama Banco de Dados");
        Thread.sleep(10000);
        Integer numero = new Random().nextInt(100) + 1;
        
        saida.println("Comando C2 Finalizado - Banco de Dados");

        return numero.toString();
    }
}
