package transitofacil.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class TelaSimuladoConcluido extends JFrame {

    private ArrayList<Boolean> acertos;

    private GridBagConstraints gbc;
    private GridBagLayout gbl;

    private JLabel lbTitulo;

    private JPanel pnResultados;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo;

    private JButton btVoltar;

    public TelaSimuladoConcluido(ArrayList<Boolean> acertos) {
        super("Trânsito Fácil - Simulado");

        this.acertos = acertos;

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

        lbTitulo = new JLabel("Simulado concluído");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPrincipal.getInstance());
            }
        });

        pnResultados = new JPanel(new FlowLayout());
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);
        modelo.addColumn("Questao");
        modelo.addColumn("Resultado");
        int i = 1;
        int nAcertos = 0, nErros = 0;
        for (Boolean b : acertos) {
            String result;
            if (b) {
                result = "Acertou";
                nAcertos++;
            } else {
                result = "Errou";
                nErros++;
            }
            modelo.addRow(new Object[]{i, result});
            i++;
        }
        barraRolagem = new JScrollPane(tabela);
        barraRolagem.setPreferredSize(new Dimension(150, 275));

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Acertos", nAcertos);
        dataset.setValue("Erros", nErros);

        JFreeChart grafico = ChartFactory.createPieChart("Resultado", dataset, true, true, true);
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setSectionPaint("Acertos", Color.GREEN);
        plot.setSectionPaint("Erros", Color.RED);

        pnResultados.add(barraRolagem);
        ChartPanel painelGrafico = new ChartPanel(grafico);
        painelGrafico.setPreferredSize(new Dimension(350, 275));
        pnResultados.add(painelGrafico);

        adicionarComponente(lbTitulo, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 0, 1, 0, 0, 0, 0);
        adicionarComponente(pnResultados, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 0, 0, 1, 0, 0, 0, 0);
        adicionarComponente(btVoltar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 3, 0, 0, 1, 0, 0, 0, 0);
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
