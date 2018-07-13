package br.dcc.ufla.ppoo.learnGeometry.gui;

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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.AncestorListener;

public class TelaVolumeInicial extends JFrame {
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    private JButton btnVoltar;
    private JButton btnIniciar;
    
    private JLabel lbVolume;
    private JLabel lbIntro;
    
    private JFrame telaAnterior;
    
    public TelaVolumeInicial(JFrame frame, String string) {
        super(string);
        telaAnterior = frame;
        
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
    
    private void construirTela() {
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        
        lbVolume = new JLabel("Volume");
        lbVolume.setFont(new Font("Courier", Font.PLAIN, 50));
        lbIntro = new JLabel("Nesta sessão haverá um teste referente ao\n" +
                                "reconhecimento das fórmulas de volumes geométricos.");
        //Intro.setFont(new Font("Courier", Font.PLAIN, 20));
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setPreferredSize(new Dimension(100, 40));
        btnVoltar.setBackground(Color.RED);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reabrirTelaAnterior();
            }
        });
        
        btnIniciar =  new JButton("Iniciar");
        btnIniciar.setPreferredSize(new Dimension(100, 40));
        btnIniciar.setBackground(Color.GREEN);
        btnIniciar.setForeground(Color.WHITE);
        
        adicionarComponente(lbVolume, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 1, 1);
        adicionarComponente(lbIntro, GridBagConstraints.WEST, GridBagConstraints.BOTH, 1, 0, 1, 1);
        adicionarComponente(btnVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1);
        adicionarComponente(btnIniciar, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);

        
        
    }
    
    private void reabrirTelaAnterior() {
        this.dispose();
        telaAnterior.setVisible(true);
        
    }
    
    private void adicionarComponente(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt) {
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(3, 3, 3, 3); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restriçõs previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }

    
}