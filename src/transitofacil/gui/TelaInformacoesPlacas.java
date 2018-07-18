package transitofacil.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TelaInformacoesPlacas extends Tela {

    public ArrayList<String> linkImagens;

    private JButton btVoltar;
    private JPanel painelImagemPlacas;
    private JLabel lbImagemPlaca;
    private JScrollPane jsp;
    private JLabel lbTitulo;

    public TelaInformacoesPlacas(ArrayList<String> linkImagens) {
        super("Trânsito Fácil - Placas");
        this.linkImagens = linkImagens;
        construirTela();
    }

    private void construirTela() {
        lbTitulo = new JLabel("Placas" + descobrirTitulo());
        lbTitulo.setFont(new Font("", Font.BOLD, 28));
        painelImagemPlacas = new JPanel(new GridLayout(5, 1, 20, 20));

        for (String link : linkImagens) {
            try {
                String IMG_PATH = linkImagens.get(0);
                BufferedImage img = ImageIO.read(new File(IMG_PATH));
                lbImagemPlaca = new JLabel(new ImageIcon(img));
                painelImagemPlacas.add(lbImagemPlaca);
            } catch (IOException e) {
                System.out.println("erro");
            }
        }

        btVoltar = new JButton("< Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPlacas.getInstance());
            }
        });

        jsp = new JScrollPane(painelImagemPlacas);
        jsp.setPreferredSize(new Dimension(700, 450));

        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                0, 0, 2, 1, 3, 0, 0, 0, 0.5, 0.5, 0, 0);
        adicionarComponente(jsp, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                1, 0, 2, 1, 0, 0, 30, 0, 0.0, 0.0, 0, 0);
        adicionarComponente(btVoltar, GridBagConstraints.LAST_LINE_END, GridBagConstraints.NONE,
                2, 1, 1, 1, 10, 0, 3, 3, 0.0, 0.0, 0, 0);
    }
    
    private String descobrirTitulo() {
        String[] imagemDividida = linkImagens.get(0).split("/");
        String titulo;
        switch (imagemDividida[3]) {
            case "placaRegulamentacao.png":
                titulo = " de Regulamentação";
                break;
            case "placaAdvertencia.png":
                titulo = " de Advertência";
                break;
            case "placaIndicacao.png":
                titulo = " de Indicação";
                break;
            case "placaAtrativos.png":
                titulo = " de Atrativos turísticos";
                break;
            default:
                titulo = " de Sinalização de Obras";
        }
        return titulo;
    }
}
