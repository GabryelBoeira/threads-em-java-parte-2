package br.com.alura.servidor.tarefa;

import br.com.alura.teste.UncaughtExceptionHandlerCustom;

import java.util.concurrent.ThreadFactory;

public record TarefaThreadFactory(ThreadFactory defaultFactory) implements ThreadFactory {

    private static int CONTADOR = 0;

    @Override
    public Thread newThread(Runnable r) {
        
        //Thread thread = new Thread(r, "Tarefa Thread " + CONTADOR);
        Thread thread = defaultFactory.newThread(r);
        thread.setName("Tarefa Thread " + CONTADOR);
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandlerCustom());
        CONTADOR++;
        return thread;
    }
}
