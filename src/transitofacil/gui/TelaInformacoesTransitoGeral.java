/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transitofacil.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author rodrigo
 */
public class TelaInformacoesTransitoGeral extends Tela{
    private String linkArquivo;
    private JButton btVoltar;

    public TelaInformacoesTransitoGeral(String linkArquivo){
        super("Transito Fácil - Trânsito Geral");
        this.linkArquivo = linkArquivo;
        construirTela();
    }
    
    private void construirTela(){
        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaTransitoGeral.getInstance());
            }
        });
        JEditorPane painelEdicoes = null;
        System.out.println(linkArquivo);
        try {
            painelEdicoes = new JEditorPane("file:///" + System.getProperty("user.dir") 
                    + "/src/transitofacil/linksTransitoGeral/" + linkArquivo);
        } catch (IOException ex) {
            System.out.println("erro");
        }
        painelEdicoes.setEditable(false);
        
        JScrollPane jsp = new JScrollPane(painelEdicoes);
        jsp.setPreferredSize(new Dimension(790, 520));
        
        adicionarComponente(jsp, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE, 
                0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        adicionarComponente(btVoltar, GridBagConstraints.LAST_LINE_END, GridBagConstraints.NONE,
                1, 0, 1, 1, 10, 0, 3, 3, 0.0, 0.0, 0, 0);
    }
    
}
