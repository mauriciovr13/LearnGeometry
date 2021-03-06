package br.dcc.ufla.ppoo.learnGeometry.gui;

import br.dcc.ufla.ppoo.learnGeometry.pergunta.Pergunta;
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** Classe TelaAreaInicial, que inicia os exercícios sobre áreas.
 * Learn Geometry
 * GCC178 - Práticas de Programação Orientada a Objetos
 * UFLA - Universidade Federal de Lavras
 * @author Maurício Vieira, Pedro Pio e Vinicius Spinelli
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

        setSize(750, 650);
        
        // Abrindo a tela no centro do screen
        setLocationRelativeTo(null);
        
        //Metodo que ler o arquivo texto com as perguntas sobre area
        perguntasAreas = new ArrayList<Pergunta>();
                
    }
    
    private void construirTela() {
        URL url = this.getClass().getResource("../imagens/LearnGeometry.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
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
                carregaPerguntas();
                construirTelaPerguntas();
            }
        });
        
        adicionarComponente(lbArea, GridBagConstraints.WEST, GridBagConstraints.NONE, 0, 0, 4, 2);
        adicionarComponente(lbIntro, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 4, 2);
        adicionarComponente(btnVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 2);
        adicionarComponente(btnIniciar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 2);
        
    }
    
    private void construirTelaPerguntas() {
        TelaPergunta tp = new TelaPergunta(this.getTitle(), perguntasAreas);
        tp.setLocationRelativeTo(this);
        setVisible(false);
        tp.setVisible(true);
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
            br = new BufferedReader(new FileReader((System.getProperty("user.dir")
                    + "/src/br/dcc/ufla/ppoo/learnGeometry/pergunta/perguntas/perguntasAreas.txt")));
            while (br.ready()) {
                String descricao = br.readLine();
                String[] alternativas = br.readLine().split(";");
                int posCorreta = Integer.parseInt(br.readLine());
                String caminhoImagem = (System.getProperty("user.dir") + br.readLine());
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
