package br.com.alura.cliente;

import java.net.Socket;

public class ClienteTarefa {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);

        System.out.println("Conectado ao servidor");

        socket.close();
    }
}
