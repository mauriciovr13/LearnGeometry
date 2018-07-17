/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufla.ppoo.learnGeometry.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author aluno
 */
public class TelaGabarito extends JFrame {
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;    
    
    private JPanel painel;
    private JLabel[] respostas;
    private JButton btnFinalizar;

    public TelaGabarito(String string) throws HeadlessException {
        super(string);
        
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

    }

    private void construirTela() {
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        btnFinalizar = new JButton("Finalizar Teste");
        btnFinalizar.setBackground(Color.BLUE);
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TelaAssunto.getInstancia().setLocationRelativeTo(null);
                dispose();
                TelaAssunto.getInstancia().setVisible(true);
            }
        });
        
        ArrayList<String> resp = lerLog();
        
        respostas = new JLabel[resp.size()];
        painel = new JPanel(new GridLayout(10, 10, 10, 10));
        for (int i = 0; i < resp.size(); i++) {
            String[] resp1 = resp.get(i).split(";");
            resp1[0] = (i+1) + "- " + resp1[0].substring(2);
            resp1[1] = (i+1) + "- " + resp1[1].substring(2);
            respostas[i] = new JLabel(resp1[1]);
            respostas[i].setPreferredSize(new Dimension(150, 40));
            if (resp1[0].equals(resp1[1])) {
                respostas[i].setBackground(Color.GREEN);
                respostas[i].setOpaque(true);
                //respostas[i].setForeground(Color.yellow);
            }
            else {
                respostas[i].setText(resp1[0]);
                respostas[i].setBackground(Color.RED);
                respostas[i].setOpaque(true);
                //respostas[i].setForeground(Color.yellow);
            }
            painel.add(respostas[i]);
        }
        
        adicionarComponente(painel, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 5, 5);
        adicionarComponente(btnFinalizar, GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 0, 1, 2);
        
        
        
        
    }
    
    private ArrayList<String> lerLog() {
        ArrayList<String> resp = new ArrayList<String>();
        BufferedReader br = null;
        File f = new File("log.txt");
        int qtd = 0;
        try {
            
            br = new BufferedReader(new FileReader(f));
            while (br.ready()) {
                resp.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Erro ao contar o numero de linhas");
        } finally {
            if (br != null) {
                try {
                    br.close();
                    f.delete();                    
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
        return resp;
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
}
