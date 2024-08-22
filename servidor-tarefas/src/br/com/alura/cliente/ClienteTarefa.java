package br.com.alura.cliente;

import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefa {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conectado ao servidor");

        Scanner teclado = new Scanner(System.in);
        String mensagem = teclado.nextLine();

        System.out.println("Enviando mensagem: " + mensagem);
        socket.close();
    }
}
