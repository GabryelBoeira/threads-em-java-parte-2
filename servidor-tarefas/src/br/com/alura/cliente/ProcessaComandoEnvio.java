package br.com.alura.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public record ProcessaComandoEnvio(Socket socket) implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Poder enviar comandos...");
            PrintStream saida = new PrintStream(socket.getOutputStream());
            Scanner teclado = new Scanner(System.in);
            String mensagem = "";

            while (teclado.hasNextLine()) {
                mensagem = teclado.nextLine();
                if (mensagem.isEmpty() || mensagem.trim().equals("")) break;
                saida.println(mensagem);
            }

            teclado.close();
            saida.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
