package br.dcc.ufla.ppoo.learnGeometry.gui;

import br.dcc.ufla.ppoo.learnGeometry.pergunta.Pergunta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaTeoremaInicial extends JFrame {
    
    private static TelaTeoremaInicial instancia = null;
    
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    private JButton btnVoltar;
    private JButton btnIniciar;
    
    private JLabel lbTeorema;
    private JLabel lbIntro;
    
    private ArrayList<Pergunta> perguntasTeorema;
    
    //padrao singleton
    public static TelaTeoremaInicial getInstancia() {
        if (instancia == null) {
            instancia = new TelaTeoremaInicial("Learn Geometry");
        }
        return instancia;
    }

    public TelaTeoremaInicial(String string) {
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
        
        // Abrindo a tela no centro do screen
        setLocationRelativeTo(null);
        
        // Lendo as perguntas de um arquivo de texto
        carregaPerguntas();
        
    }
    
    private void construirTela() {
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);
        
        lbTeorema = new JLabel("Teorema/Leis");
        lbTeorema.setFont(new Font("Courier", Font.PLAIN, 50));
        lbIntro = new JLabel("<html>Nesta sessão haverá um teste referente ao reconhecimento" +
                                "<br>dos Teoremas e Leis na geometria.</html>");
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
        
        adicionarComponente(lbTeorema, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 4, 2);
        adicionarComponente(lbIntro, GridBagConstraints.WEST, GridBagConstraints.BOTH, 2, 0, 2, 2);
        adicionarComponente(btnVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 2);
        adicionarComponente(btnIniciar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 2);

        
        
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
            br = new BufferedReader(new FileReader("perguntasAreas.txt"));
            while (br.ready()) {
                String descricao = br.readLine();
                String[] alternativas = br.readLine().split(";");
                int posCorreta = Integer.parseInt(br.readLine());
                String caminhoImagem = br.readLine();
                Pergunta p = new Pergunta(descricao, alternativas, posCorreta, caminhoImagem);
                perguntasTeorema.add(p);
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
