package transitofacil.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TelaPlacas extends Tela {

    private static TelaPlacas instancia = null;

    private JLabel lbTitulo;

    private JButton btRegulamentacao;
    private JButton btAdvertencia;
    private JButton btIndicacao;
    private JButton btAtrativos;
    private JButton btSinalizacao;
    private JButton btVoltar;

    //SINGLETON
    public static TelaPlacas getInstance() {
        if (instancia == null) {
            instancia = new TelaPlacas();
        }
        return instancia;
    }

    //SINGLETON
    private TelaPlacas() {
        super("Trânsito Fácil - Placas");
        construirTela();
    }

    private void construirTela() {
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
                chamarOutraJanela(TelaPlacasRegulamentacao.getInstance());
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
}
