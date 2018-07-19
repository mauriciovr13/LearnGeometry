package br.dcc.ufla.ppoo.learnGeometry.pergunta;

import java.io.Serializable;

/**
 * Learn Geometry
 * GCC178 - Práticas de Programação Orientada a Objetos
 * UFLA - Universidade Federal de Lavras
 * @author Maurício Vieira, Pedro Pio e Vinicius Spinelli
 */

public class Pergunta implements Serializable {
    private String descricao;
    private String[] alternativas;
    private int posicaoCorreta;
    private String caminhoImagem;

    public Pergunta(String descricao, String[] alternativas, int posicaoCorreta, String caminhoImagem) {
        this.descricao = descricao;
        this.alternativas = alternativas;
        this.posicaoCorreta = posicaoCorreta;
        this.caminhoImagem = caminhoImagem;
    }
    
    public int getResposta() {
        return posicaoCorreta;
    }
    
    public String getAlternativaIndex(int i) {
        return alternativas[i];
    }

    public String getDescricao() {
        return descricao;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    
    public boolean verificaReposta(String resp) {
        if (resp.equals(alternativas[posicaoCorreta])) {
            return true;
        }
        return false;
    }
    
    

}
