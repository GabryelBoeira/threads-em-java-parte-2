package br.com.alura.servidor;

public record TarefaPararServidorTeste(ServidorDeTeste servidor) implements Runnable {

    @Override
    public void run() {
        //chamando o método estaRodando()
        System.out.println("Servidor comecando, estaRodando=" + this.servidor.estaRodando());
        while (!this.servidor.estaRodando()) {
        }

        System.out.println("Servidor rodando, estaRodando=" + this.servidor.estaRodando());

        while (this.servidor.estaRodando()) {
        }

        System.out.println("Servidor terminando, estaRodando=" + this.servidor.estaRodando());
    }
}
