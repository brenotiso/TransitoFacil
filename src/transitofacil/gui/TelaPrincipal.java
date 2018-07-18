package transitofacil.gui;

import transitofacil.simulado.*;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import transitofacil.excecoes.ArquivoException;

public class TelaPrincipal extends Tela {

    private static TelaPrincipal instancia = null;

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

    private JPanel painelDescricao;
    private GridBagLayout gblPainel;
    private JLabel jlPainelTitulo;
    private JLabel jlPainelDescricao;
    private ImageIcon imagemPrincipal;
    private ImageIcon imagemVazia;

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
        construirTela();
    }

    private void construirTela() {
        lbTitulo = new JLabel("Aprendizado de Trânsito");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        btPlacas = new JButton("Placas");
        btTransitoGeral = new JButton("Trânsito geral");
        btSimuladoPlacas = new JButton("Simulado de placas");
        btSimuladoTransitoGeral = new JButton("Simulado de Trânsito geral");
        
        imagemPrincipal = new ImageIcon("src/transitofacil/imgs/ImagemPrincipal.png");
        imagemVazia = new ImageIcon("src/transitofacil/imgs/nullImage.png");
        

        gblPainel = new GridBagLayout();
        painelDescricao = new JPanel(gblPainel);
        jlPainelTitulo = new JLabel("Bem vindo!");
        jlPainelDescricao = new JLabel(imagemPrincipal);
        adicionarComponentePainelDescricao(jlPainelTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                0, 0, 1, 1, 10, 5, 10, 10, 1.0, 1.0, 0, 0);
        adicionarComponentePainelDescricao(jlPainelDescricao, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                1, 0, 1, 1, 0, 0, 0, 0, 1.0, 1.0, 0, 0);

        btPlacas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPlacas.getInstance());
            }
        });
        btPlacas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jlPainelTitulo.setText("btPlacas");
                jlPainelDescricao.setIcon(imagemVazia);
                jlPainelDescricao.setText("<html>Line1 <br/> Line2 <br/> Line3</html>");
                jlPainelDescricao.setHorizontalTextPosition(JLabel.CENTER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlPainelTitulo.setText("Bem vindo!");
                jlPainelDescricao.setText("");
                jlPainelDescricao.setIcon(imagemPrincipal);
            }
        });

        btTransitoGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer");
            }
        });
        btTransitoGeral.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jlPainelTitulo.setText("btTransitoGeral");
                jlPainelDescricao.setIcon(imagemVazia);
                jlPainelDescricao.setText("<html>Line1 <br/> Line2 <br/> Line3</html>");
                jlPainelDescricao.setHorizontalTextPosition(JLabel.CENTER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlPainelTitulo.setText("Bem vindo!");
                jlPainelDescricao.setText("");
                jlPainelDescricao.setIcon(imagemPrincipal);
            }
        });

        btSimuladoPlacas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaSimulado("placas"));
            }
        });
        btSimuladoPlacas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jlPainelTitulo.setText("btSimuladoPlacas");
                jlPainelDescricao.setIcon(imagemVazia);
                jlPainelDescricao.setText("<html>Line1 <br/> Line2 <br/> Line3</html>");
                jlPainelDescricao.setHorizontalTextPosition(JLabel.CENTER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlPainelTitulo.setText("Bem vindo!");
                jlPainelDescricao.setText("");
                jlPainelDescricao.setIcon(imagemPrincipal);
            }
        });

        btSimuladoTransitoGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaSimulado("geral"));
            }
        });
        btSimuladoTransitoGeral.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jlPainelTitulo.setText("btSimuladoTransitoGeral");
                jlPainelDescricao.setIcon(imagemVazia);
                jlPainelDescricao.setText("<html>Line1 <br/> Line2 <br/> Line3</html>");
                jlPainelDescricao.setHorizontalTextPosition(JLabel.CENTER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlPainelTitulo.setText("Bem vindo!");
                jlPainelDescricao.setText("");
                jlPainelDescricao.setIcon(imagemPrincipal);
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
        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                0, 0, 2, 1, 3, 3, 3, 3, 0.0, 0.5, 0, 0);
        adicionarComponente(btPlacas, GridBagConstraints.LINE_START, GridBagConstraints.VERTICAL,
                1, 0, 1, 1, 20, 75, 0, 0, 1.0, 1.0, 145, 10);
        adicionarComponente(painelDescricao, GridBagConstraints.LINE_START, GridBagConstraints.BOTH,
                1, 1, 1, 4, 20, 0, 0, 50, 1.0, 1.0, 0, 0);
        adicionarComponente(btTransitoGeral, GridBagConstraints.LINE_START, GridBagConstraints.VERTICAL,
                2, 0, 1, 1, 0, 75, 0, 0, 1.0, 1.0, 91, 10);
        adicionarComponente(btSimuladoPlacas, GridBagConstraints.LINE_START, GridBagConstraints.VERTICAL,
                3, 0, 1, 1, 0, 75, 0, 0, 1.0, 1.0, 54, 10);
        adicionarComponente(btSimuladoTransitoGeral, GridBagConstraints.LINE_START, GridBagConstraints.VERTICAL,
                4, 0, 1, 1, 0, 75, 50, 0, 1.0, 1.0, 0, 10);
        adicionarComponente(spFooter, GridBagConstraints.PAGE_END, GridBagConstraints.HORIZONTAL,
                5, 0, 2, 1, 0, 0, 3, 0, 0.0, 0.0, 0, 0);
        adicionarComponente(lbGrupo1, GridBagConstraints.PAGE_END, GridBagConstraints.NONE,
                6, 0, 2, 1, 0, 3, 3, 3, 0.0, 0.0, 0, 0);
        adicionarComponente(lbGrupo2, GridBagConstraints.PAGE_END, GridBagConstraints.NONE,
                7, 0, 2, 1, 0, 3, 3, 3, 0.0, 0.0, 0, 0);
        adicionarComponente(lbGrupo3, GridBagConstraints.PAGE_END, GridBagConstraints.NONE,
                8, 0, 2, 1, 0, 0, 0, 0, 0.0, 0.0, 0, 0);
        adicionarComponente(lbAddQuestoes, GridBagConstraints.PAGE_END, GridBagConstraints.NONE,
                9, 0, 2, 1, 10, 0, 3, 3, 0.0, 0.0, 0, 0);
    }

    private void adicionarComponentePainelDescricao(Component comp, int anchor, int fill, int linha, int coluna, int larg, int alt,
            int top, int left, int bot, int right, double wX, double wY, int ipX, int ipY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = anchor; // posicionamento do componente na tela (esquerda, direita, centralizado, etc)
        gbc.fill = fill; // define se o tamanho do componente será expandido ou não
        gbc.gridy = linha; // linha do grid onde o componente será inserido
        gbc.gridx = coluna; // coluna do grid onde o componente será inserido
        gbc.gridwidth = larg; // quantidade de colunas do grid que o componente irá ocupar
        gbc.gridheight = alt; // quantidade de linhas do grid que o componente irá ocupar
        gbc.insets = new Insets(top, left, bot, right); // espaçamento (em pixels) entre os componentes da tela
        gbc.weightx = wX;
        gbc.weighty = wY;
        gbc.ipadx = ipX;
        gbc.ipady = ipY;
        gblPainel.setConstraints(comp, gbc); // adiciona o componente "comp" ao layout com as restrições previamente especificadas
        painelDescricao.add(comp); // efetivamente insere o componente na tela
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

                    if (tipo.contentEquals("placa")) {
                        Questoes.addQuestao(novaQuestao, 0);
                    } else if (tipo.contentEquals("geral")) {
                        Questoes.addQuestao(novaQuestao, 1);
                    } else {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                JOptionPane.showMessageDialog(this, "Importado com sucesso de " + escolherArquivo.getSelectedFile(),
                        "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } catch (ArquivoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "Tipo de questão inválida!\nEntre com 'placa' ou 'geral'.",
                        "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao ler arquivo!",
                        "Ops... algo deu errado :(", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (br != null) {
                    try {
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
                bw.write("placa               //tipo da questao(placa ou geral)\n"
                        + "img.png             //nome da imagem (imagens disponiveis podem ser vistas/adicionadas na pasta placas), caso nao queira basta deixar a linha em branco\n"
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
