package transitofacil.gui;

import transitofacil.simulado.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
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
    private JButton btTerminar;

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

        jtpTabs = new JTabbedPane();

        File f = new File(tipoSimulado + ".bin");
        if (f.exists() && !f.isDirectory()) {

            try {
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

                    ButtonGroup group = new ButtonGroup();
                    group.add(alternativa1);
                    group.add(alternativa2);
                    group.add(alternativa3);
                    group.add(alternativa4);

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

            // Adicionando os componentes à tela
            adicionarComponente(jtpTabs, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1, 0, 0, 0, 0);
        } else {
            //arrumar futuramente
            JLabel nada = new JLabel("Nenhuma pergunta cadastrada!");
            adicionarComponente(nada, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1, 0, 0, 0, 0);
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
