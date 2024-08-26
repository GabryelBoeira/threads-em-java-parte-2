package br.com.alura.servidor;

import java.io.PrintStream;

public record ComandoC2(PrintStream saida) implements Runnable {

    @Override
    public void run() {
        System.out.println("Executando o comando C2");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        saida.println("C2 executado com sucesso");
    }

}
