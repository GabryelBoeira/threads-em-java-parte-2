package br.com.alura.teste;

public class UncaughtExceptionHandlerCustom implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        System.out.println("Exceção na Thread: " + t.getName() + ", mensagem: " + e.getMessage());

    }
}
