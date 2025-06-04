package com.orchestra.inorchestration.arquivos;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

@Getter
@Setter
public class Arquivos {

    private String nome;
    private String conteudo;
    private Path caminho;
    private String formato;
    private File arquivo;

    public Arquivos(String nome, String conteudo, Path caminho, String formato, File arquivo) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.caminho = caminho;
        this.formato = formato;
        this.arquivo = arquivo;
    }

    public Arquivos() {}

    public Arquivos(String nome, Path caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }

    public void colocarConteudo() {
        if (conteudo == null || conteudo.isEmpty()) {
            conteudo = "Conteúdo não disponível";
        }
    }

    public void copiarConteudoParaOutroArquivo(File destino) {
        if (arquivo == null || !arquivo.exists()) {
            System.err.println("Arquivo de origem inválido.");
            return;
        }
        if (destino == null) {
            System.err.println("Arquivo de destino inválido.");
            return;
        }
        if (destino.isDirectory()) {
            System.err.println("Arquivo de destino não pode ser um diretório.");
            return;
        }

        try {
            Files.copy(arquivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erro ao copiar o arquivo: " + e.getMessage());
        }
    }

    public void atualizarArquivo(String novoConteudo) {
        if (arquivo == null || !arquivo.exists()) {
        System.err.println("Arquivo inexistente.");
        return;
        }

        try {
        Files.write(
            arquivo.toPath(),
            novoConteudo.getBytes(),
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        );
        } catch (IOException e) {
        System.err.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    
    }

}
