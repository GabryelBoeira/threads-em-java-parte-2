package br.com.alura.servidor.comando;

import java.io.PrintStream;

public record ComandoC2(PrintStream saida) implements Runnable {

    @Override
    public void run() {
        System.out.println("Executando o comando C2");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("C2 falhou depois de 5 segundos");

        //saida.println("C2 executado com sucesso");
    }

}
