package br.com.alura.testes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TesteFila {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> fila = new ArrayBlockingQueue<>(3);

        fila.put("C1");
        fila.put("C2");
        fila.put("C3");

        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.take());
    }
}
