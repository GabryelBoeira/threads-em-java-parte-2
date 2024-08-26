package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefa {

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private AtomicBoolean estaExecutando;

    public ServidorTarefa() throws IOException {
        System.out.println("---Iniciando o servidor ---");
        this.serverSocket = new ServerSocket(5000);
        this.executorService = Executors.newCachedThreadPool();
        this.estaExecutando = new AtomicBoolean(true);
    }

    public void iniciarServidor() throws IOException {
        while (this.estaExecutando.get()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Aceitando nova conexão na porta: "+ socket.getPort());

                executorService.execute(new DistribuirTarefa(socket, this));
            } catch (SocketException e) {
                System.out.println("Servidor finalizado");
            }
        }
    }

    public void finalizarServidor() throws IOException {
        estaExecutando.set(false);
        serverSocket.close();
        executorService.shutdown();
    }

}
