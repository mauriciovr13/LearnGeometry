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
