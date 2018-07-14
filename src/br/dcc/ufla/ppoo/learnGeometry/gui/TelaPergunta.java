/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufla.ppoo.learnGeometry.gui;

import br.dcc.ufla.ppoo.learnGeometry.principal.Pergunta;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author aluno
 */
public class TelaPergunta extends JFrame {
    
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    private int qtdPerguntasRespondidas = 0;
    private int qtdAcertos = 0;
    
    private ArrayList<Pergunta> perguntasAreas;
    private Pergunta p;
    private int indicePergunta;
    
    private ImageIcon imagem;
    private JLabel lbImagem;
    private JLabel lbPergunta;
    private JRadioButton rbtnAlt1;
    private JRadioButton rbtnAlt2;
    private JRadioButton rbtnAlt3;
    private JRadioButton rbtnAlt4;
    private JButton btnVoltar;
    private JButton btnPular;
    private JButton btnProximo;
    private JButton btnFinalizarTeste;

    public TelaPergunta(String string, ArrayList<Pergunta> perguntasAreas) {
        super(string);
        this.perguntasAreas = perguntasAreas;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Evita que a tela possa ser redimensionada pelo usuário
        setResizable(false);

        // Invoca o método que efetivamente constrói a tela
        construirTela();

        // Inicia o relógio da tela
        //iniciarRelogio();
        setSize(750, 650);
        // Redimensiona automaticamente a tela, com base nos componentes existentes na mesma
        //pack();
        
        // Abrindo a tela no centro do screen
        setLocationRelativeTo(null);
    }
    
    private int getPergunta() {
        //obtem um indice para o vetor de pergutas
        
        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        return gerador.nextInt(perguntasAreas.size());
    
    }

    private void construirTela() {
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        indicePergunta = getPergunta();
        p = perguntasAreas.get(indicePergunta);
        
        imagem = new ImageIcon(p.getCaminhoImagem());
        lbImagem = new JLabel(imagem);
        lbPergunta = new JLabel(p.getDescricao());
        String[] alternativas = p.getAlternativas();
        rbtnAlt1 = new JRadioButton(alternativas[0]);
        rbtnAlt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                rbtnAlt2.setSelected(false);
                rbtnAlt3.setSelected(false);
                rbtnAlt4.setSelected(false);
            }
        });
        
        rbtnAlt2 = new JRadioButton(alternativas[1]);
        rbtnAlt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                rbtnAlt1.setSelected(false);
                rbtnAlt3.setSelected(false);
                rbtnAlt4.setSelected(false);
            }
        });
        
        rbtnAlt3 = new JRadioButton(alternativas[2]);
        rbtnAlt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                rbtnAlt1.setSelected(false);
                rbtnAlt2.setSelected(false);
                rbtnAlt4.setSelected(false);
                
            }
        });
        
        rbtnAlt4 = new JRadioButton(alternativas[3]);
        rbtnAlt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                rbtnAlt1.setSelected(false);
                rbtnAlt2.setSelected(false);
                rbtnAlt3.setSelected(false);
            }
        });
        
        btnVoltar = new JButton("Voltar");
        if (qtdPerguntasRespondidas == 0) {
            btnVoltar.setEnabled(true);
        }
        else {
            btnVoltar.setEnabled(false);
        }
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                voltarTelaAnterior();
            }
        });
        btnPular = new JButton("Pular");
        
        btnProximo = new JButton("Proximo");
        btnProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                verificaPergunta();
            }
        });
        
        adicionarComponente(lbImagem, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 3, 3);
        adicionarComponente(lbPergunta, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);
        adicionarComponente(rbtnAlt1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 0, 1, 1);
        adicionarComponente(rbtnAlt2, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 1, 1, 1);
        adicionarComponente(rbtnAlt3, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 0, 1, 1);
        adicionarComponente(rbtnAlt4, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5, 1, 1, 1);
                
    }
    
    private void verificaPergunta() {
        if (rbtnAlt1.isSelected() && p.validarResposta(rbtnAlt1.getText())) {
            qtdAcertos += 1;
        }
        perguntasAreas.remove(indicePergunta);
        removeAll();
        construirTela();
    }
    
    private void voltarTelaAnterior() {
        
    }
    
    private void adicionarComponente(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt) {
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(3, 3, 3, 3); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restrições previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }
    
}

