package transitofacil.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private ArrayList<String> linkImagensRegulamentacao = new ArrayList();
    private ArrayList<String> linkImagensAdvertencia = new ArrayList();
    private ArrayList<String> linkImagensIndicacao = new ArrayList();
    private ArrayList<String> linkImagensAtrativos = new ArrayList();
    private ArrayList<String> linkImagensSinalizacaoObras = new ArrayList();

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
        
        linkImagensRegulamentacao.add("src/transitofacil/imgs/placaRegulamentacao1.png");
        linkImagensRegulamentacao.add("src/transitofacil/imgs/placaRegulamentacao2.png");
        linkImagensRegulamentacao.add("src/transitofacil/imgs/placaRegulamentacao3.png");
        linkImagensRegulamentacao.add("src/transitofacil/imgs/placaRegulamentacao4.png");
        linkImagensRegulamentacao.add("src/transitofacil/imgs/placaRegulamentacao5.png");
        
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia1.png");
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia2.png");
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia3.png");
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia4.png");
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia5.png");
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia6.png");
        linkImagensAdvertencia.add("src/transitofacil/imgs/placaAdvertencia7.png");
        
        linkImagensIndicacao.add("src/transitofacil/imgs/placaIndicacao1.png");
        linkImagensIndicacao.add("src/transitofacil/imgs/placaIndicacao2.png");
        linkImagensIndicacao.add("src/transitofacil/imgs/placaIndicacao3.png");
        
        linkImagensAtrativos.add("src/transitofacil/imgs/placaAtrativos1.png");
        linkImagensAtrativos.add("src/transitofacil/imgs/placaAtrativos2.png");
        linkImagensAtrativos.add("src/transitofacil/imgs/placaAtrativos3.png");
        linkImagensAtrativos.add("src/transitofacil/imgs/placaAtrativos4.png");
        linkImagensAtrativos.add("src/transitofacil/imgs/placaAtrativos5.png");
        linkImagensAtrativos.add("src/transitofacil/imgs/placaAtrativos6.png");
        
        linkImagensSinalizacaoObras.add("src/transitofacil/imgs/placaObra1.png");
        linkImagensSinalizacaoObras.add("src/transitofacil/imgs/placaObra2.png");
        linkImagensSinalizacaoObras.add("src/transitofacil/imgs/placaObra3.png");

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
                chamarOutraJanela(new TelaInformacoesPlacas(linkImagensRegulamentacao));
            }
        });

        btAdvertencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaInformacoesPlacas(linkImagensAdvertencia));
            }
        });

        btIndicacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaInformacoesPlacas(linkImagensIndicacao));
            }
        });

        btAtrativos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaInformacoesPlacas(linkImagensAtrativos));
            }
        });

        btSinalizacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(new TelaInformacoesPlacas(linkImagensSinalizacaoObras));
            }
        });

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPrincipal.getInstance());
            }
        });

        // Adicionando os componentes à tela
        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                0, 0, 2, 1, 3, 3, 3, 3, 0.0, 0.5, 0, 0);
        adicionarComponente(btRegulamentacao, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                1, 0, 1, 1, 3, 140, 3, 3, 0.5, 0.5, 27, 50);
        adicionarComponente(btAdvertencia, GridBagConstraints.LINE_END, GridBagConstraints.NONE,
                1, 1, 1, 1, 3, 3, 3, 140, 0.5, 0.5, 51, 50);
        adicionarComponente(btIndicacao, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                2, 0, 1, 1, 3, 140, 3, 3, 0.5, 0.5, 80, 50);
        adicionarComponente(btAtrativos, GridBagConstraints.LINE_END, GridBagConstraints.NONE,
                2, 1, 1, 1, 3, 3, 3, 140, 0.5, 0.5, 0, 50);
        adicionarComponente(btSinalizacao, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                3, 0, 2, 1, 3, 3, 70, 3, 0.5, 0.5, 0, 50);
        adicionarComponente(btVoltar, GridBagConstraints.LAST_LINE_END, GridBagConstraints.NONE,
                4, 1, 1, 1, 10, 0, 3, 3, 0.0, 0.0, 0, 0);
    }
}
