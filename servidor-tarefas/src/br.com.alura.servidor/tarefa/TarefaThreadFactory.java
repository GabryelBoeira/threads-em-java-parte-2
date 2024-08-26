package br.com.alura.servidor.tarefa;

import br.com.alura.teste.UncaughtExceptionHandlerCustom;

import java.util.concurrent.ThreadFactory;

public class TarefaThreadFactory implements ThreadFactory {

    private static int contador = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "Tarefa Thread " + contador);
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandlerCustom());
        contador++;
        return thread;
    }
}
