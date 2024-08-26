package br.com.alura.servidor;

public class ServidorDeTeste {

    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTeste servidor = new ServidorDeTeste();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        new Thread(new TarefaPararServidorTeste(this)).start();
    }

    public synchronized boolean estaRodando() {
        return this.estaRodando;
    }

    public synchronized void parar() {
        this.estaRodando = false;
    }

    public synchronized void ligar() {
        this.estaRodando = true;
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        this.ligar();

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        this.parar();
    }
}