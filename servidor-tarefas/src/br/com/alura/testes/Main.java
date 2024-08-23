package br.com.alura.testes;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Vizualizando todas as threads ---");

        Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

        for (Thread thread : todasAsThreads) {
            System.out.println(thread.getName());
        }

        Runtime runtime = Runtime.getRuntime();
        int qtdProcessadores = runtime.availableProcessors();
        System.out.println("\nQtd de processadores: " + qtdProcessadores);
    }
}
