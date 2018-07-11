package br.dcc.ufla.ppoo.learnGeometry.gui;

public class Perguntas {
    private String descricao;
    private String[] alternativas;
    private int posicaoCorreta;

    public Perguntas(String descricao, String[] alternativas, int posicaoCorreta) {
        this.descricao = descricao;
        this.alternativas = alternativas;
        this.posicaoCorreta = posicaoCorreta;
    }

}
