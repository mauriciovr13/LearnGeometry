package br.dcc.ufla.ppoo.learnGeometry.pergunta;

import java.io.Serializable;

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
    
    public boolean validarResposta(String resposta) {
        if (alternativas[posicaoCorreta].equalsIgnoreCase(resposta)) {
            return true;
        }
        return false;
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
    
    

}
