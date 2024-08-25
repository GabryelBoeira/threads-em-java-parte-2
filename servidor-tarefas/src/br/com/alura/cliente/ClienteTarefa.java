package br.com.alura.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefa {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conectado ao servidor");

        Thread threadEnvioComando = new Thread(() -> {
            processaComandoEnvio(socket);
        });

        Thread threadRespostaComando = new Thread(() -> {
            processaComandoResposta(socket);
        });

        threadEnvioComando.start();
        threadRespostaComando.start();

        threadEnvioComando.join();
        socket.close();
    }

    /**
     * Processa o comando de envio para o servidor.
     *
     * @param socket	a conexão com o servidor
     */
    private static void processaComandoEnvio(Socket socket) {
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

    /**
     * Processa a resposta do servidor.
     *
     * @param  socket   a conexão com o servidor
     */
    private static void processaComandoResposta(Socket socket) {
        try {
            System.out.println("Recebendo resposta do servidor...");
            Scanner respostaServidor = new Scanner(socket.getInputStream());

            while (respostaServidor.hasNextLine()) {
                System.out.println("\n" + respostaServidor.nextLine());
            }

            respostaServidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
