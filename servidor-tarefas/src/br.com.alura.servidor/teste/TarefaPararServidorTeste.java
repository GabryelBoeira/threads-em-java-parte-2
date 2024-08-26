package br.com.alura.servidor.teste;

public record TarefaPararServidorTeste(ServidorDeTeste servidor) implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Servidor comecando, estaRodando=" + this.servidor.estaRodando());
            while (!this.servidor.estaRodando()) {
            }

            if (this.servidor.estaRodando()) {
                throw new RuntimeException("Deu ruim na Thread .....");
            }

            System.out.println("Servidor rodando, estaRodando=" + this.servidor.estaRodando());
            while (this.servidor.estaRodando()) {
            }

            System.out.println("Servidor terminando, estaRodando=" + this.servidor.estaRodando());
        } catch (Exception e) {
            System.out.println("Catch na Thread "+ Thread.currentThread().getId() + " : " + e.getMessage());
        }
    }
}
