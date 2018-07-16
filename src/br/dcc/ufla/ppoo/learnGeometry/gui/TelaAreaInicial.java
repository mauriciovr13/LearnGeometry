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
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;

/**
 *
 * @author aluno
 */
public class TelaAreaInicial extends JFrame {
    
    private static TelaAreaInicial instancia = null;
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    private JButton btnVoltar;
    private JButton btnIniciar;
    
    private JLabel lbArea;
    private JLabel lbIntro;
    
    private ArrayList<Pergunta> perguntasAreas;
    
    //padrao singleton
    public static TelaAreaInicial getInstancia() {
        if (instancia == null) {
            instancia = new TelaAreaInicial("Learn Geometry");
        }
        return instancia;
    }

    public TelaAreaInicial(String string) {
        super(string);
        
        // Define que fechar a janela, a execução aplicação será encerrada
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Evita que a tela possa ser redimensionada pelo usuário
        setResizable(true);

        // Invoca o método que efetivamente constrói a tela
        construirTela();

        // Inicia o relógio da tela
        //iniciarRelogio();
        setSize(750, 650);
        // Redimensiona automaticamente a tela, com base nos componentes existentes na mesma
        //pack();
        
        //Metodo que ler o arquivo binario com as perguntas sobre area
        carregaPerguntas();        
        
    }
    
    private void construirTela() {
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        
        lbArea = new JLabel("Área");
        lbArea.setFont(new Font("Courier", Font.PLAIN, 50));
        lbIntro = new JLabel("<html>Nesta sessão haverá um teste referente ao reconhecimento"
                           + "<br>das fórmulas de áres geométricas. </html>");
        lbIntro.setFont(new Font("Courier", Font.PLAIN, 20));
        btnVoltar = new JButton("Voltar");
        btnVoltar.setPreferredSize(new Dimension(150, 40));
        btnVoltar.setBackground(Color.RED);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reabrirTelaAnterior();
            }
        });
        
        btnIniciar =  new JButton("Iniciar");
        btnIniciar.setPreferredSize(new Dimension(150, 40));
        btnIniciar.setBackground(Color.GREEN);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TelaResultado tl = new TelaResultado(25, 25);
                setVisible(false);
                tl.setVisible(true);
                //construirTelaPerguntas();
            }
        });
        
        adicionarComponente(lbArea, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 4, 2);
        adicionarComponente(lbIntro, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 4, 2);
        adicionarComponente(btnVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 2);
        adicionarComponente(btnIniciar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 2);
        
    }
    
    private void construirTelaPerguntas() {
        new TelaPergunta(this.getTitle(), perguntasAreas).setVisible(true);
        dispose();
    }
    
    private void reabrirTelaAnterior() {
        this.dispose();
        TelaAssunto.getInstancia().setVisible(true);        
    }
    
    private void adicionarComponente(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt) {
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(10, 10, 10, 10); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restriçõs previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }

    private void carregaPerguntas() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("../pergunta/perguntasAreas.txt"));
            System.out.println("*");
            while (br.ready()) {
                System.out.println("1");
                String descricao = br.readLine();
                System.out.println("2");
                String[] alternativas = br.readLine().split(";");
                System.out.println("3");
                int posCorreta = Integer.parseInt(br.readLine());
                System.out.println("4");
                String caminhoImagem = br.readLine();
                System.out.println("5");
                Pergunta p = new Pergunta(descricao, alternativas, posCorreta, caminhoImagem);
                perguntasAreas.add(p);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "O arquivo que contem as perguntas não foi encontrado!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao fechar o arquivo!");
                }
            }
        }
    }

}
