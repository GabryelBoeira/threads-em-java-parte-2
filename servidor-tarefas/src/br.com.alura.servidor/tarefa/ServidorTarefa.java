package br.com.alura.servidor.tarefa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefa {

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private AtomicBoolean estaExecutando;
    private BlockingQueue<String> filaComandos;

    public ServidorTarefa() throws IOException {
        System.out.println("---Iniciando o servidor ---");
        this.serverSocket = new ServerSocket(5000);
        ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        this.executorService = Executors.newCachedThreadPool(); //newFixedThreadPool(4, new TarefaThreadFactory(defaultFactory));
        this.estaExecutando = new AtomicBoolean(true);
        this.filaComandos = new ArrayBlockingQueue<>(2);
        iniciarConsumidor();
    }

    private void iniciarConsumidor() {
        for (int i = 0; i < 2; i++) {
            TarefaConsumir tarefaConsumir = new TarefaConsumir(filaComandos);
            this.executorService.execute(tarefaConsumir);
        }
    }

    public void iniciarServidor() throws IOException {
        while (this.estaExecutando.get()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Aceitando nova conexão na porta: "+ socket.getPort());

                executorService.execute(new DistribuirTarefa(executorService, socket, this, filaComandos));
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
