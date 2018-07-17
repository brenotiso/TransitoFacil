package transitofacil.gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPlacasRegulamentacao extends Tela {
    private static TelaPlacasRegulamentacao instancia = null;

    private JLabel lbImagemPlacas;
    private JButton btVoltar;
    
    public static JFrame getInstance() {
        if (instancia == null) {
            instancia = new TelaPlacasRegulamentacao();
        }
        return instancia;
    }
    private TelaPlacasRegulamentacao(){
        super("Trânsito Fácil - Placas de Regulamentação");

        construirTela();
    }
    
    private void construirTela(){
        definirLayout();
        try{
            String IMG_PATH = "src/transitofacil/imgs/categoriaAtrativos.png";
            BufferedImage img = ImageIO.read(new File(IMG_PATH));
            lbImagemPlacas = new JLabel(new ImageIcon(img));
        }catch(IOException e){
            System.out.println("erro");
        }
        
        btVoltar = new JButton("< Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPlacas.getInstance());
            }
        });
        
        adicionarComponente(lbImagemPlacas, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 1, 1, 1, 1, 1, 1);//Deve estar zoado
        adicionarComponente(btVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1, 3, 3, 3, 3);
    }
}
