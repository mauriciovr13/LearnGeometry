package br.dcc.ufla.ppoo.learnGeometry.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** Classe TelaAssunto, indica sobre qual assunto será feito exercícios.
 * Learn Geometry
 * GCC178 - Práticas de Programação Orientada a Objetos
 * UFLA - Universidade Federal de Lavras
 * @author Maurício Vieira, Pedro Pio e Vinicius Spinelli
 */

public class TelaAssunto extends JFrame {
    
    private static TelaAssunto instancia = null;
    
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    private JLabel lbTexto;
    
    private JButton btnArea;
    private JButton btnVolume;
    private JButton btnTeorema;
    
    //padrao singleton
    public static TelaAssunto getInstancia() {
        if (instancia == null) {
            instancia = new TelaAssunto("Learn Geometry");
        }
        return instancia;
    }

    public TelaAssunto(String title) throws HeadlessException {
        super(title);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Evita que a tela possa ser redimensionada pelo usuário
        setResizable(false);

        // Invoca o método que efetivamente constrói a tela
        construirTela();

        setSize(750, 650);
        
        // Abrindo a tela no centro do screen
        setLocationRelativeTo(null);
        
    }
    
    private void construirTela() {
        URL url = this.getClass().getResource("../imagens/LearnGeometry.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
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
        TelaTeoremaInicial.getInstancia().setLocationRelativeTo(this);
        setVisible(false);
        TelaTeoremaInicial.getInstancia().setVisible(true);
    }
    
    private void criarTelaAreas() {
        TelaAreaInicial.getInstancia().setLocationRelativeTo(this);
        setVisible(false);
        TelaAreaInicial.getInstancia().setVisible(true);
    }
    
    private void criarTelaVolume() {
        TelaVolumeInicial.getInstancia().setLocationRelativeTo(this);
        setVisible(false);
        TelaVolumeInicial.getInstancia().setVisible(true);        
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

