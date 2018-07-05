package transitofacil.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {

    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private JPanel pnPrincipal;
    private JLabel lbTitulo;

    public TelaPrincipal() {
        super("Trânsito Fácil");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();

        construirTela();
    }

    private void limparTela() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }

    private void construirTela() {
        pnPrincipal = new JPanel(gbl);
        setContentPane(pnPrincipal);
         lbTitulo = new JLabel("Aprendizado de Trânsito");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        JButton btPlacas = new JButton("Placas");
        JButton btTransitoGeral = new JButton("Trânsito geral");
        JButton btSimuladoPlacas = new JButton("Simulado de placas");
        JButton btSimuladoTransitoGeral = new JButton("Simulado de Trânsito geral");

        btPlacas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limparTela();
                exibirTelaPlacas();
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
                System.out.println("fazer!");
            }
        });

        btSimuladoTransitoGeral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });

        JSeparator spFooter = new JSeparator(SwingConstants.HORIZONTAL);
        JLabel lbGrupo1 = new JLabel("Software desenvolvido para a disciplina de PPOO, ministrada pelo professor Paulo Afonso.");
        JLabel lbGrupo2 = new JLabel("Breno Tana, Matheus Fernandes e Rodrigo Marafelli.");
        JLabel lbGrupo3 = new JLabel("UFLA - 2018");

        // Adicionando os componentes à tela
        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, 0, 0, 1, 1, 3, 3, 3, 3);
//        adicionarComponente(btPlacas, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, 1, 0, 1, 1, 3, 3, 3, 3);
//        adicionarComponente(btTransitoGeral, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1, 0, 3, 3, 3);
//        adicionarComponente(btSimuladoPlacas, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1, 0, 3, 3, 3);
//        adicionarComponente(btSimuladoTransitoGeral, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1, 0, 3, 3, 3);
//        adicionarComponente(spFooter, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 5, 0, 1, 1, 0, 0, 3, 0);
//        adicionarComponente(lbGrupo1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 6, 0, 1, 1, 0, 3, 3, 3);
//        adicionarComponente(lbGrupo2, GridBagConstraints.CENTER, GridBagConstraints.NONE, 7, 0, 1, 1, 0, 3, 3, 3);
//        adicionarComponente(lbGrupo3, GridBagConstraints.CENTER, GridBagConstraints.NONE, 8, 0, 1, 1, 0, 0, 3, 0);

        Dimension d = new Dimension(800, 600);
        setSize(d);
        //pack();
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
        pnPrincipal.add(comp); // efetivamente insere o componente na tela
    }

    private void exibirTelaPlacas() {
        JLabel lbTitulo = new JLabel("Placas");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        JButton btRegulamentacao = new JButton("Placas de Regulamentação");
        JButton btAdvertencia = new JButton("Placas de advertência");
        JButton btIndicacao = new JButton("Placas de indicação");
        JButton btAtrativos = new JButton("Placas de atrativos turísticos");
        JButton btSinalizacao = new JButton("Placas de sinalização de obras");
        JButton btVoltar = new JButton("< Voltar");

        btRegulamentacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });

        btAdvertencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });

        btIndicacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });

        btAtrativos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });
        
        btSinalizacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("fazer!");
            }
        });
        
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limparTela();
                construirTela();
            }
        });

        // Adicionando os componentes à tela
//        adicionarComponente(lbTitulo, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 1, 1);
//        adicionarComponente(btRegulamentacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 1);
//        adicionarComponente(btAdvertencia, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1);
//        adicionarComponente(btIndicacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1);
//        adicionarComponente(btAtrativos, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1);
//        adicionarComponente(btSinalizacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 0, 1, 1);
//        adicionarComponente(btVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1);

        pack();
    }

}
