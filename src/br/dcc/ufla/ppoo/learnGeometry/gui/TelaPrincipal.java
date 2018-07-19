package br.dcc.ufla.ppoo.learnGeometry.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** Classe TelaPrincipal, página inicial que se inicia o programa.
 * Learn Geometry
 * GCC178 - Práticas de Programação Orientada a Objetos
 * UFLA - Universidade Federal de Lavras
 * @author Maurício Vieira, Pedro Pio e Vinicius Spinelli
 */

public class TelaPrincipal extends JFrame {
    
    private static TelaPrincipal instancia = null;
    
    // Componentes referentes ao layout da tela
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    
    private JLabel lbImagenInicial;
    private JLabel lbInicial;
    private JLabel lbVazio;
    
    private JButton btnIniciar;
    
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Evita que a tela possa ser redimensionada pelo usuário
        setResizable(false);

        // Invoca o método que efetivamente constrói a tela
        construirTela();

        setSize(750, 650);
        
        // Abrindo a tela no centro do screen
        setLocationRelativeTo(null);
    }
    
    private void construirTela(){
        URL url = this.getClass().getResource("../imagens/LearnGeometry.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
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
    }
        
    private void criarProximaTela() {
        TelaAssunto.getInstancia().setLocationRelativeTo(this);
        this.setVisible(false);
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
}
