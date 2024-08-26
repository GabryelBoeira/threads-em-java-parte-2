package br.com.alura.servidor;

import br.com.alura.servidor.tarefa.ServidorTarefa;

public class Main {

    public static void main(String[] args) throws Exception {

        ServidorTarefa servidor = new ServidorTarefa();
        servidor.iniciarServidor();
        servidor.finalizarServidor();
    }
}
