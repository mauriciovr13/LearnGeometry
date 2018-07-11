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
public class TelaAssunto extends JFrame {
    
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    private JLabel lbTexto;
    
    private JButton btnArea;
    private JButton btnVolume;
    private JButton btnTeorema;

    public TelaAssunto(String title) throws HeadlessException {
        super(title);
        
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
    }
    
    private void construirTela() {
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        lbTexto = new JLabel("Escolha qual matéria você quer estudar:");
        lbTexto.setFont(new Font("SansSerif", Font.PLAIN, 30));
        
        btnArea = new JButton("Area");
        btnArea.setPreferredSize(new Dimension(150, 40));
        btnArea.setBackground(Color.GREEN);
        btnArea.setForeground(Color.WHITE);
        btnArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarTelaAreas();
            }
        });
        
        btnVolume = new JButton("Volume");
        btnVolume.setPreferredSize(new Dimension(150, 40));
        btnVolume.setForeground(Color.WHITE);
        btnVolume.setBackground(Color.GREEN);
        btnVolume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarTelaVolume();
            }
        });
        
        btnTeorema = new JButton("Teoremas/Leis");
        btnTeorema.setPreferredSize(new Dimension(150, 40));
        btnTeorema.setBackground(Color.GREEN);
        btnTeorema.setForeground(Color.WHITE);
        btnTeorema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarTelaTeorema();
            }
        });
        
        
        adicionarComponente(lbTexto, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 5, 1);
        adicionarComponente(btnArea, GridBagConstraints.WEST, GridBagConstraints.BOTH, 5, 0, 2, 2);
        adicionarComponente(btnVolume, GridBagConstraints.WEST, GridBagConstraints.BOTH, 7, 0, 1, 2);
        adicionarComponente(btnTeorema, GridBagConstraints.WEST, GridBagConstraints.BOTH, 9, 0, 1, 2);
        
    }
    
    private void criarTelaTeorema() {
        dispose();
    }
    
    private void criarTelaAreas() {
        JFrame telaAreas = new TelaAreasInicial(this.getTitle());
        telaAreas.setLocationRelativeTo(this);
        telaAreas.setVisible(true);
    }
    
    private void criarTelaVolume() {
        
    }
    
    private void adicionarComponente(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt) {
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(10, 10, 10, 10); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restrições previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }
    
    
    
}

