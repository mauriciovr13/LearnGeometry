package br.dcc.ufla.ppoo.learnGeometry.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        setSize(750, 650);
        
        // Abrindo a tela no centro do screen
        setLocationRelativeTo(null);
    }

    private void construirTela(int qtdAcertos, int qtdPerguntas) {
        URL url = this.getClass().getResource("../imagens/LearnGeometry.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        lbTexto = new JLabel("Seu resultado final do teste foi:");
        lbTexto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        lbresultado = new JLabel(qtdAcertos + " Corretas / " + (qtdPerguntas-qtdAcertos) + " Erradas");
        
        int aproveitamento = (qtdAcertos * 100)/qtdPerguntas;
        lbAproveitamento = new JLabel("Você obteve " + aproveitamento + "% de aproveitamento");
        
        btnRefazer =  new JButton("Refazer teste");
        btnRefazer.setBackground(Color.GREEN);
        btnRefazer.setForeground(Color.WHITE);
        btnRefazer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaRefazerTeste();
            }
        });
        
        btnEncerrar = new JButton("Encerrar Teste");
        btnEncerrar.setBackground(Color.RED);
        btnEncerrar.setForeground(Color.WHITE);
        btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarTelaAssunto();
                File f = new File("log.txt");
                f.delete();
            }
        });
        
        btnGabarito = new JButton("Conferir Gabarito");
        btnGabarito.setBackground(Color.BLUE);
        btnGabarito.setForeground(Color.WHITE);
        btnGabarito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGabarito();
            }
        });
        JPanel painel = new JPanel(new GridLayout(1, 3, 15, 15));
        painel.add(btnRefazer);
        painel.add(btnEncerrar);
        painel.add(btnGabarito);
        
        
        adicionarComponente(lbTexto, GridBagConstraints.WEST, GridBagConstraints.BOTH, 0, 0, 4, 2);
        adicionarComponente(lbresultado, GridBagConstraints.WEST, GridBagConstraints.BOTH, 2, 0, 1, 1);
        adicionarComponente(lbAproveitamento, GridBagConstraints.WEST, GridBagConstraints.BOTH, 3, 0, 1, 1);
        adicionarComponente(painel, GridBagConstraints.CENTER, GridBagConstraints.NONE, 4, 0, 2, 1);
        
    }
    
    private void criarTelaAssunto() {
        TelaAssunto.getInstancia().setLocationRelativeTo(this);
        this.dispose();
        TelaAssunto.getInstancia().setVisible(true);
    }
    
    private void telaRefazerTeste() {
        TelaAreaInicial.getInstancia().setLocationRelativeTo(this);
        this.dispose();
        TelaAreaInicial.getInstancia().setVisible(true);
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
        TelaGabarito tg = new TelaGabarito(this.getTitle());
        tg.setLocationRelativeTo(this);
        this.dispose();
        tg.setVisible(true);
    }

}
