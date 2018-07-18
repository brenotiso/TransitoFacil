package transitofacil.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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

public class TelaSimuladoConcluido extends Tela {

    private ArrayList<Boolean> acertos;
    private JLabel lbTitulo;

    private JPanel pnResultados;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo;

    private JButton btVoltar;

    public TelaSimuladoConcluido(ArrayList<Boolean> acertos) {
        super("Trânsito Fácil - Simulado");

        this.acertos = acertos;

        construirTela();
    }

    private void construirTela() {
        definirLayout();
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

        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE, 0, 0, 0, 1, 0, 0, 0, 0, 0.0, 0.0, 0, 0);
        adicionarComponente(pnResultados, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 0, 0, 1, 0, 0, 0, 0, 0.0, 0.0, 0, 0);
        adicionarComponente(btVoltar, GridBagConstraints.PAGE_END, GridBagConstraints.NONE, 3, 0, 0, 1, 0, 0, 0, 0, 0.0, 0.0, 0, 0);
    }
}
