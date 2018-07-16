/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufla.ppoo.learnGeometry.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Maurício Vieira
 */
public class TelaResultado extends JFrame{
    
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    private JLabel lbTexto;
    private JLabel lbresultado;
    private JLabel lbAproveitamento;
    private JButton btnRefazer;
    private JButton btnGabarito;
    private JButton btnEncerrar;

    public TelaResultado(int qtdAcertos, int qtdPerguntas) {
        super("Learn Geometry");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Evita que a tela possa ser redimensionada pelo usuário
        setResizable(false);

        // Invoca o método que efetivamente constrói a tela
        construirTela(qtdAcertos, qtdPerguntas);

        // Seta o tamanho padrão da tela
        setSize(750, 650);
        
        // Redimensiona automaticamente a tela, com base nos componentes existentes na mesma
        //pack();
    }

    private void construirTela(int qtdAcertos, int qtdPerguntas) {
        
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        lbTexto = new JLabel("Seu resultado final do teste foi:");
        lbTexto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        lbresultado = new JLabel(qtdAcertos + " Corretas / " + (qtdPerguntas-qtdAcertos) + " Erradas");
        
        int aproveitamento = (qtdAcertos * 100)/qtdPerguntas;
        lbAproveitamento = new JLabel("Você obteve " + aproveitamento + "% de aproveitamento");
        
        btnRefazer =  new JButton("Refazer teste");
        btnRefazer.setBackground(Color.RED);
        btnRefazer.setForeground(Color.WHITE);
        btnRefazer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaRefazerTeste();
            }
        });
        
        btnEncerrar = new JButton("Encerrar Teste");
        btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        btnGabarito = new JButton("Conferir Gabarito");
        btnGabarito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGabarito();
            }
        });
        
        
        
        adicionarComponente(lbTexto, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 4, 2);
        adicionarComponente(lbresultado, GridBagConstraints.WEST, GridBagConstraints.BOTH, 2, 0, 1, 1);
        adicionarComponente(lbAproveitamento, GridBagConstraints.WEST, GridBagConstraints.BOTH, 3, 0, 1, 1);
        adicionarComponente(btnRefazer, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1);
        adicionarComponente(btnEncerrar, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 1, 1, 1);
        
    }
    
    private void telaRefazerTeste() {
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
        gbc.insets = new Insets(5, 5, 5, 5); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restrições previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }
    
    private void mostrarGabarito() {
        //construir a tela de gabarito
        //lendo um txt
    }
    
    
    
}
