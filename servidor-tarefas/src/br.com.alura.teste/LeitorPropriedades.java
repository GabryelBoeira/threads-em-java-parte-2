package br.com.alura.teste;

import java.io.FileReader;
import java.util.Properties;

public class LeitorPropriedades implements Runnable {

    private Properties propriedades;
    private String nomeArquivo;

    public LeitorPropriedades(Properties propriedades, String nomeArquivo) {
        this.propriedades = propriedades;
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void run() {
        try {
            this.propriedades.load(new FileReader(nomeArquivo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}