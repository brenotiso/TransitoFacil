package transitofacil.gui;

import transitofacil.simulado.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
