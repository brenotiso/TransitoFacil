/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transitofacil.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rodrigo
 */
public class TelaInformacoesPlacas extends JFrame{
    public ArrayList<String> linkImagens = new ArrayList();
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private JButton btVoltar;
    private JPanel painelImagemPlacas;
    private JLabel lbImagemPlaca;
    private JScrollPane jsp;
    
    
    public TelaInformacoesPlacas(ArrayList<String> linkImagens){
        super("Trânsito Fácil - Placas de Regulamentação");
        this.linkImagens = linkImagens;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        construirTela(linkImagens);
        Dimension d = new Dimension(800, 600);
        setSize(d);
    }
    
    private void construirTela(ArrayList<String> linkImagens){
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        setLayout(gbl);
        painelImagemPlacas = new JPanel(new GridLayout(5,1));
        
        for(String link: linkImagens){
            try{
                String IMG_PATH = link;
                BufferedImage img = ImageIO.read(new File(IMG_PATH));
                lbImagemPlaca = new JLabel(new ImageIcon(img));
                painelImagemPlacas.add(lbImagemPlaca);
            }catch(IOException e){
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
        
        
        
        adicionarComponente(jsp, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 1, 1, 1, 1, 1, 1);//Deve estar zoado
        adicionarComponente(btVoltar, GridBagConstraints.WEST, GridBagConstraints.NONE, 6, 0, 1, 1, 3, 3, 3, 3);
        
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
    
    private void chamarOutraJanela(JFrame outraJanela){
        outraJanela.setLocationRelativeTo(this);
        outraJanela.setVisible(true);
        setVisible(false);
    }
}
