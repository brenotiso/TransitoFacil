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

public class TelaPlacas extends JFrame {
    private static TelaPlacas instancia = null;
    
    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    private JLabel lbTitulo;

    private JButton btRegulamentacao;
    private JButton btAdvertencia;
    private JButton btIndicacao;
    private JButton btAtrativos;
    private JButton btSinalizacao;
    private JButton btVoltar;

    //SINGLETON
    public static TelaPlacas getInstance(){
        if(instancia == null){
            instancia = new TelaPlacas();
        }
        return instancia;
    }
    //SINGLETON
    private TelaPlacas() {
        super("Trânsito Fácil - Placas");

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

        lbTitulo = new JLabel("Placas");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        btRegulamentacao = new JButton("Placas de Regulamentação");
        btAdvertencia = new JButton("Placas de advertência");
        btIndicacao = new JButton("Placas de indicação");
        btAtrativos = new JButton("Placas de atrativos turísticos");
        btSinalizacao = new JButton("Placas de sinalização de obras");
        btVoltar = new JButton("< Voltar");

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
                chamarOutraJanela(TelaPrincipal.getInstance());
            }
        });

        // Adicionando os componentes à tela
        adicionarComponente(lbTitulo, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btRegulamentacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 1, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btAdvertencia, GridBagConstraints.WEST, GridBagConstraints.NONE, 2, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btIndicacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 3, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btAtrativos, GridBagConstraints.WEST, GridBagConstraints.NONE, 4, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btSinalizacao, GridBagConstraints.WEST, GridBagConstraints.NONE, 5, 0, 1, 1, 3, 3, 3, 3);
        adicionarComponente(btVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1, 3, 3, 3, 3);
    }
    
    private void chamarOutraJanela(JFrame outraJanela){
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
