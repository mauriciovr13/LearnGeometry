/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufla.ppoo.learnGeometry.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


public class TelaPrincipal extends JFrame {
    
    private static TelaPrincipal instancia = null;
    
    // Componentes referentes ao layout da tela
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    private JLabel lbImagenInicial;
    private JLabel lbInicial;
    private JLabel lbVazio;
    
    private JButton btnIniciar;
    /*
    private JRadioButton rbtnAlt1;
    private JRadioButton rbtnAlt2;
    private JRadioButton rbtnAlt3;
    private JRadioButton rbtnAlt4;
    */
    
    public static TelaPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new TelaPrincipal();
        }
        return instancia;
    }
    
    public TelaPrincipal() {
        // Define o título da tela
        super("Learn Geometry");

        // Define que fechar a janela, a execução aplicação será encerrada
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
    
    private void construirTela(){
        setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        lbVazio = new JLabel(" ");
        
        lbImagenInicial = new JLabel(new ImageIcon(getClass().getResource("../imagens/cubo.gif")));
        lbInicial = new JLabel("Estudando Geometria");
        lbInicial.setFont(new Font("Courier", Font.PLAIN, 50));
        
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setPreferredSize(new Dimension(100, 40));
        btnIniciar.setBackground(Color.GREEN);
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarProximaTela();
            }
        });
        
        adicionarComponente(lbImagenInicial, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 4, 4);
        adicionarComponente(lbInicial, GridBagConstraints.CENTER, GridBagConstraints.NONE, 6, 0, 4, 2);
        adicionarComponente(btnIniciar, GridBagConstraints.EAST, GridBagConstraints.NONE, 8, 0, 4, 3);
        /*
        rbtnAlt1 = new JRadioButton("teste1");
        rbtnAlt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Oi");
                //remover tela
                getContentPane().removeAll();
                revalidate();
                repaint();
            }
        });
        rbtnAlt2 = new JRadioButton("Teste2");
        rbtnAlt3 = new JRadioButton("Teste3");
        rbtnAlt4 = new JRadioButton("Teste4");
        //Thread t = new Thread(this);
        //t.start();
        
        adicionarComponente(rbtnAlt1, GridBagConstraints.WEST, GridBagConstraints.NONE, 12, 0, 1, 1);
        adicionarComponente(rbtnAlt2, GridBagConstraints.WEST, GridBagConstraints.NONE, 12, 1, 1, 1);
        adicionarComponente(rbtnAlt3, GridBagConstraints.WEST, GridBagConstraints.NONE, 13, 0, 1, 1);
        adicionarComponente(rbtnAlt4, GridBagConstraints.WEST, GridBagConstraints.NONE, 13, 1, 1, 1);
        */
    }
        
    private void criarProximaTela() {
        this.setVisible(false);
        TelaAssunto.getInstancia().setLocationRelativeTo(this);
        TelaAssunto.getInstancia().setVisible(true);
        //telaAssunto.setLocationRelativeTo(null);
        //telaAssunto.setVisible(true);
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
