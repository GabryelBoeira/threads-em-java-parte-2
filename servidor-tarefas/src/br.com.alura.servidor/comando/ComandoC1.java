package br.com.alura.servidor.comando;

import java.io.PrintStream;

public record ComandoC1(PrintStream saida) implements Runnable {

    @Override
    public void run() {
        System.out.println("Executando o comando C1");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        saida.println("C1 executado com sucesso");
    }

}
