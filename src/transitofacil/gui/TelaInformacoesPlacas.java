package transitofacil.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class TelaInformacoesPlacas extends Tela {

    public String linkImagem;

    private JButton btVoltar;
    private JLabel lbImagem;
    private JScrollPane jsp;
    private JLabel lbTitulo;

    public TelaInformacoesPlacas(String linkImagem) {
        super("Trânsito Fácil - Placas");
        this.linkImagem = linkImagem;
        construirTela();
    }

    private void construirTela() {
        lbTitulo = new JLabel("Placas" + descobrirTitulo());
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(linkImagem));
        } catch (IOException ex) {
            System.out.println("erro");
        }
        
        lbImagem = new JLabel(new ImageIcon(img));
        
        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPlacas.getInstance());
            }
        });

        jsp = new JScrollPane(lbImagem);
        jsp.setPreferredSize(new Dimension(780, 460));

        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                0, 0, 2, 1, 3, 0, 0, 0, 0.5, 0.5, 0, 0);
        adicionarComponente(jsp, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                1, 0, 2, 1, 0, 0, 30, 0, 0.0, 0.0, 0, 0);
        adicionarComponente(btVoltar, GridBagConstraints.LAST_LINE_END, GridBagConstraints.NONE,
                2, 1, 1, 1, 10, 0, 3, 3, 0.0, 0.0, 0, 0);
    }
    
    private String descobrirTitulo() {
        String[] imagemDividida = linkImagem.split("/");
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
