package transitofacil.gui;

import transitofacil.simulado.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import transitofacil.excecoes.ArquivoException;

public class TelaSimulado extends JFrame {

    private String tipoSimulado;

    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    private JLabel lbTitulo;

    private JTabbedPane jtpTabs;
    private ArrayList<ButtonGroup> alGruposRespostas;
    private ArrayList<ButtonModel> alRespostasCorretas;

    private JButton btVoltar;
    private JButton btTerminar;

    private Thread tTerminarSimulado;

    public TelaSimulado(String tipoSimulado) {
        super("Trânsito Fácil - Simulado");

        this.tipoSimulado = tipoSimulado;

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

        lbTitulo = new JLabel("Simulado");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        btTerminar = new JButton("Terminar");
        btTerminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int i = 0;
                ArrayList<Boolean> acertos = new ArrayList<Boolean>();
                for (ButtonGroup b : alGruposRespostas) {
                    acertos.add(b.getSelection().equals(alRespostasCorretas.get(i)));
                    i++;
                }
                chamarOutraJanela(new TelaSimuladoConcluido(acertos));
            }
        });

        jtpTabs = new JTabbedPane();

        File f = new File(tipoSimulado + ".bin");
        if (f.exists() && !f.isDirectory()) {

            try {
                alGruposRespostas = new ArrayList<>();
                alRespostasCorretas = new ArrayList<>();
                ArrayList<Questao> questoes = Questoes.obterQuestoesAleatorias(tipoSimulado + ".bin");
                int i = 1;
                for (Questao q : questoes) {
                    JPanel jp = new JPanel(new GridLayout(0, 1));
                    jp.add(new JLabel(q.getPergunta()));

                    //adicionar imagem aq
                    //jp.add(q.getImagem());
                    ArrayList<String> alternativas = q.getAlternativas();
                    JRadioButton alternativa1 = new JRadioButton(alternativas.get(0));
                    JRadioButton alternativa2 = new JRadioButton(alternativas.get(1));
                    JRadioButton alternativa3 = new JRadioButton(alternativas.get(2));
                    JRadioButton alternativa4 = new JRadioButton(alternativas.get(3));
                    ArrayList<JRadioButton> radioAlternativas = new ArrayList<>();
                    radioAlternativas.add(alternativa1);
                    radioAlternativas.add(alternativa2);
                    radioAlternativas.add(alternativa3);
                    radioAlternativas.add(alternativa4);

                    ButtonGroup group = new ButtonGroup();
                    group.add(alternativa1);
                    group.add(alternativa2);
                    group.add(alternativa3);
                    group.add(alternativa4);

                    alGruposRespostas.add(group);
                    alRespostasCorretas.add(radioAlternativas.get(q.getAlternativaCorreta() - 1).getModel());

                    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
                    radioPanel.add(alternativa1);
                    radioPanel.add(alternativa2);
                    radioPanel.add(alternativa3);
                    radioPanel.add(alternativa4);
                    jp.add(radioPanel);

                    jtpTabs.addTab(Integer.toString(i), jp);
                    i++;
                }
            } catch (ArquivoException ex) {
                //tratar
            }

            tTerminarSimulado = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (verificaGupos()) {
                            adicionarComponente(btTerminar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 1, 1, 1, 0, 0, 0, 0);
                            revalidate();
                            repaint();
                            return;
                        }
                    }
                }

                private boolean verificaGupos() {
                    for (ButtonGroup b : alGruposRespostas) {
                        if ((b.getSelection() == null)) {
                            return false;
                        }
                    }
                    return true;
                }
            });
            tTerminarSimulado.start();

            jtpTabs.setPreferredSize(new Dimension(650, 430));
            // Adicionando os componentes à tela
            adicionarComponente(jtpTabs, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1, 0, 0, 0, 0);
        } else {
            //arrumar futuramente
            btVoltar = new JButton("Voltar");
            btVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    chamarOutraJanela(TelaPrincipal.getInstance());
                }
            });
            JLabel nada = new JLabel("Nenhuma pergunta cadastrada!");
            adicionarComponente(nada, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1, 0, 0, 0, 0);
            adicionarComponente(btVoltar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 1, 1, 1, 0, 0, 0, 0);
        }

        adicionarComponente(lbTitulo, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 0, 1, 0, 0, 0, 0);
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

}
