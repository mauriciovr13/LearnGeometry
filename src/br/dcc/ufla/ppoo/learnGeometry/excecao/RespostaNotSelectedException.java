package br.dcc.ufla.ppoo.learnGeometry.excecao;

/** Classe RespostaNotSelectedException, que lança exceção caso tente
 *  avançar de exercício e a resposta não esteja selecionada.
 * Learn Geometry
 * GCC178 - Práticas de Programação Orientada a Objetos
 * UFLA - Universidade Federal de Lavras
 * @author Maurício Vieira, Pedro Pio e Vinicius Spinelli
 */

public class RespostaNotSelectedException extends Exception {
    public RespostaNotSelectedException(String message){
        super(message);
    }
    
}
