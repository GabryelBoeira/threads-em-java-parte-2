package br.com.alura.teste;

import java.io.FileReader;
import java.util.Properties;

public class Principal {

    public static void main(String[] args) {
        Properties properties = new Properties();
        Thread thread = new Thread(new LeitorPropriedades(properties, "arquivo1.txt"));
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandlerCustom());
        thread.start();
    }
}

