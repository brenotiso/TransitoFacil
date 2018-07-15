package transitofacil.gui;

import transitofacil.simulado.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import transitofacil.excecoes.ArquivoException;

public class TelaPrincipal extends JFrame {

    private static TelaPrincipal instancia = null;

    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    private JLabel lbTitulo;
    private JButton btPlacas;
    private JButton btTransitoGeral;
    private JButton btSimuladoPlacas;
    private JButton btSimuladoTransitoGeral;

    private JSeparator spFooter;
    private JLabel lbGrupo1;
    private JLabel lbGrupo2;
    private JLabel lbGrupo3;

    private JLabel lbAddQuestoes;

    //SINGLETON
    public static TelaPrincipal getInstance() {
        if (instancia == null) {
            instancia = new TelaPrincipal();
        }
        return instancia;
    }

    //SINGLETON
    private TelaPrincipal() {
        super("Trânsito Fácil");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        construirTela();
        Dimension d = new Dimension(800, 600);
        setSize(d);
        //pack();
    }

    private void construirTela() {
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();

        setLayout(gbl);

        lbTitulo = new JLabel("Aprendizado de Trânsito");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        btPlacas = new JButton("Placas");
        btTransitoGeral = new JButton("Trânsito geral");
        btSimuladoPlacas = new JButton("Simulado de placas");
        btSimuladoTransitoGeral = new JButton("Simulado de Trânsito geral");

        btPlacas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPlacas.getInstance());
            }
        });

        btTransitoGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });

        btSimuladoPlacas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaSimulado("placas"));
            }
        });

        btSimuladoTransitoGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaSimulado("geral"));
            }
        });

        spFooter = new JSeparator(SwingConstants.HORIZONTAL);
        lbGrupo1 = new JLabel("Software desenvolvido para a disciplina de PPOO, ministrada pelo professor Paulo Afonso.");
        lbGrupo2 = new JLabel("Breno Tana, Matheus Fernandes e Rodrigo Marafelli.");
        lbGrupo3 = new JLabel("UFLA - 2018");

        lbAddQuestoes = new JLabel("Adicionar questões");
        lbAddQuestoes.setForeground(Color.blue);
        lbAddQuestoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarQuestoes();
            }
        });

        // Adicionando os componentes à tela
        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, 0, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btPlacas, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, 1, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btTransitoGeral, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1, 0, 3, 3, 3);
        adicionarComponente(btSimuladoPlacas, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1, 0, 3, 3, 3);
        adicionarComponente(btSimuladoTransitoGeral, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1, 0, 3, 3, 3);
        adicionarComponente(spFooter, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 5, 0, 1, 1, 0, 0, 3, 0);
        adicionarComponente(lbGrupo1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 6, 0, 1, 1, 0, 3, 3, 3);
        adicionarComponente(lbGrupo2, GridBagConstraints.CENTER, GridBagConstraints.NONE, 7, 0, 1, 1, 0, 3, 3, 3);
        adicionarComponente(lbGrupo3, GridBagConstraints.CENTER, GridBagConstraints.NONE, 8, 0, 1, 1, 0, 0, 3, 0);
        adicionarComponente(lbAddQuestoes, GridBagConstraints.CENTER, GridBagConstraints.NONE, 9, 0, 1, 1, 0, 0, 3, 0);
    }

    private void chamarOutraJanela(JFrame outraJanela) {
        outraJanela.setLocationRelativeTo(this);
        outraJanela.setVisible(true);
        setVisible(false);
    }

    private void adicionarComponente(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt,
            int top, int left, int bot, int right) {
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(top, left, bot, right); // espaçamento (em pixels) entre os componentes da tela
        gbl.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restrições previamente especificadas
        add(comp); // efetivamente insere o componente na tela
    }

    private void adicionarQuestoes() {
        JOptionPane.showMessageDialog(this, "Entre com um arquivo texto que siga o padrão estipulado!\n"
                + "O padrão pode ser visto no arquivo EXEMPLO.txt",
                "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        validarExemplo(); //valida se o arquivo de exemplo existe, se nao ele eh criado

        JFileChooser escolherArquivo = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo texto", "txt");
        escolherArquivo.setFileFilter(filter);

        int result = escolherArquivo.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(escolherArquivo.getSelectedFile()));
                while (br.ready()) {
                    String tipo = br.readLine();
                    String imagem = br.readLine();
                    String pergunta = br.readLine();
                    ArrayList<String> alternativas = new ArrayList<String>();
                    alternativas.add(br.readLine());
                    alternativas.add(br.readLine());
                    alternativas.add(br.readLine());
                    alternativas.add(br.readLine());
                    int alternativaCorreta = Integer.parseInt(br.readLine());

                    br.readLine(); //ler linha em branco

                    Questao novaQuestao = new Questao(pergunta, imagem, alternativas, alternativaCorreta);

                    if (tipo.equals("0")) {
                        Questoes.addQuestao(novaQuestao, "placas.bin");
                    } else if (tipo.equals("1")) {
                        Questoes.addQuestao(novaQuestao, "geral.bin");
                    } else {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
            } catch (ArquivoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "Parece que o arquivo que você está tentando importar é inválido!",
                        "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao ler arquivo!",
                        "Ops... algo deu errado :(", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (br != null) {
                    try {
                        JOptionPane.showMessageDialog(this, "Importado com sucesso de " + escolherArquivo.getSelectedFile(),
                                "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        br.close();
                    } catch (IOException ioex) {
                        JOptionPane.showMessageDialog(this, "Erro ao fechar o arquivo de tarefas!",
                                "Ops... algo deu errado :(", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void validarExemplo() {
        File f = new File("EXEMPLO.txt");
        if (!(f.exists() && !f.isDirectory())) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("EXEMPLO.txt"));
                bw.write("0                   //tipo da questao, 0 para placa e 1 para geral\n"
                        + "img.png             //caminho da imagem, caso nao queira basta deixar a linha em branco\n"
                        + "Qual a cor do mar?  //pergunta\n"
                        + "vermelho            //alternativa 1\n"
                        + "azul                //alternativa 2\n"
                        + "amarelho            //alternativa 3\n"
                        + "preto               //alternativa 4\n"
                        + "2                   //numero da alternativa correta\n"
                        + "                    //linha em branco para separar as questoes");
            } catch (IOException e) {
                System.out.println("erro");
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException ioex) {
                        System.out.println("erro");
                    }
                }
            }
        }
    }

}
