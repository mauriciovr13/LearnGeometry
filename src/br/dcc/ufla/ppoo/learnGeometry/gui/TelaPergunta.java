/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufla.ppoo.learnGeometry.gui;

import br.dcc.ufla.ppoo.learnGeometry.pergunta.Pergunta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author aluno
 */
public class TelaPergunta extends JFrame {
    
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    public int qtdPerguntasRespondidas = 0;
    public int qtdAcertos = 0;
    
    private ArrayList<Pergunta> perguntas;
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
    private ButtonGroup group;

    public TelaPergunta(String string, ArrayList<Pergunta> perguntas) {
        super(string);
        this.perguntas = perguntas;
        
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
        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        //obtem um indice para o vetor de pergutas
        return gerador.nextInt(perguntas.size());
    
    }

    private void construirTela() {
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        indicePergunta = getPergunta();
        p = perguntas.get(indicePergunta);
        
        imagem = new ImageIcon(p.getCaminhoImagem());//é mesmo necessário?
        lbImagem = new JLabel(new ImageIcon(p.getCaminhoImagem()));
        lbPergunta = new JLabel(p.getDescricao());
        
        String[] alternativas = p.getAlternativas();
        rbtnAlt1 = new JRadioButton(alternativas[0]);
        rbtnAlt2 = new JRadioButton(alternativas[1]);
        rbtnAlt3 = new JRadioButton(alternativas[2]);
        rbtnAlt4 = new JRadioButton(alternativas[3]);
        
        group = new ButtonGroup();
        group.add(rbtnAlt1);
        group.add(rbtnAlt2);
        group.add(rbtnAlt3);
        group.add(rbtnAlt4);
        
        JPanel radioPanel1 = new JPanel(new GridLayout(2, 1));
        JPanel radioPanel2 = new JPanel(new GridLayout(3, 2));
        radioPanel1.add(lbImagem);
        radioPanel1.add(lbPergunta);
        radioPanel2.add(rbtnAlt1);
        radioPanel2.add(rbtnAlt2);
        radioPanel2.add(rbtnAlt3);
        radioPanel2.add(rbtnAlt4);
        
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
        btnProximo.setPreferredSize(new Dimension(150, 40));
        btnProximo.setBackground(Color.GREEN);
        btnProximo.setForeground(Color.WHITE);
        radioPanel2.add(btnProximo);
        btnProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                verificaPergunta();
            }
        });
        
        adicionarComponente(radioPanel1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 4, 4);
        adicionarComponente(radioPanel2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 4, 0, 4, 4);
                
    }
    
    private void verificaPergunta() {
        String log = null;
        Boolean continuar = true;
        if (rbtnAlt1.isSelected()) {
            int i = p.getResposta();
            log = p.getAlternativaIndex(i) + ";" + rbtnAlt1.getText() + "\n";
            if (p.verificaReposta(rbtnAlt1.getText())) {
                qtdAcertos += 1;
            }
        }
        else if (rbtnAlt2.isSelected()) {
            int i = p.getResposta();
            log = p.getAlternativaIndex(i) + ";" + rbtnAlt2.getText() + "\n";
            if (p.verificaReposta(rbtnAlt2.getText())) {
                qtdAcertos += 1;
            }
        }
        else if (rbtnAlt3.isSelected()) {
            int i = p.getResposta();
            log = p.getAlternativaIndex(i) + ";" + rbtnAlt3.getText() + "\n";
            if (p.verificaReposta(rbtnAlt3.getText())) {
                qtdAcertos += 1;
            }
        }
        else if (rbtnAlt4.isSelected()) {
            int i = p.getResposta();
            log = p.getAlternativaIndex(i) + ";" + rbtnAlt4.getText() + "\n";
            if (p.verificaReposta(rbtnAlt4.getText())) {
                qtdAcertos += 1;
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "É necessário escolher uma resposta!",
                                "Learn Geometry", JOptionPane.ERROR_MESSAGE);
            continuar = false;
        }
        if (continuar){
            ++qtdPerguntasRespondidas;
            escreverArquivoLog(log);
            perguntas.remove(indicePergunta);
            if (!perguntas.isEmpty()){
                proximaPergunta();
            }
            else{
                gerarResultado();   
            }
        }
    }
    
    private void escreverArquivoLog(String log) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("log.txt", true));
            bw.write(log);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no log de resposats");
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Não é para entrar aqui");
                }
            }
        }
    }
    
    private void voltarTelaAnterior() {
        TelaAssunto.getInstancia().setLocationRelativeTo(this);
        dispose();
        TelaAssunto.getInstancia().setVisible(true);
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

    private void proximaPergunta() {
        indicePergunta = getPergunta();
        p = perguntas.get(indicePergunta);
        imagem = new ImageIcon(p.getCaminhoImagem());
        lbImagem.setIcon(imagem);
        lbPergunta.setText(p.getDescricao());
        String[] alternativas = p.getAlternativas();
        rbtnAlt1.setText(alternativas[0]);
        rbtnAlt2.setText(alternativas[1]);
        rbtnAlt3.setText(alternativas[2]);
        rbtnAlt4.setText(alternativas[3]);
        group.clearSelection();        
        /*
        TelaPergunta tp = new TelaPergunta("Areas", perguntasAreas);
        setVisible(false);
        tp.setVisible(true);
        */
    }

    private void gerarResultado() {
        //pensar uma maneira de pegar a qtdPerguntas pra passar no construtor...
        TelaResultado tr = new TelaResultado(qtdAcertos, qtdPerguntasRespondidas);
        tr.setLocationRelativeTo(this);
        //setVisible(false);        
        this.dispose();
        tr.setVisible(true);
    }
    
}

