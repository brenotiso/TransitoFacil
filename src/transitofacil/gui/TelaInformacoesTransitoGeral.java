/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transitofacil.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

/**
 *
 * @author rodrigo
 */
public class TelaInformacoesTransitoGeral extends Tela{
    private String linkArquivo;
    private JButton btVoltar;
    private JLabel lbTitulo;
    
    public TelaInformacoesTransitoGeral(String linkArquivo) throws IOException {
        super("Transito Fácil - Trânsito Geral");
        this.linkArquivo = linkArquivo;
        construirTela();
    }
    
    private void construirTela() throws IOException{
        lbTitulo = new JLabel("Trânsito Geral");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));
        
        JEditorPane painelEdicoes = new JEditorPane(linkArquivo);
        painelEdicoes.setEditable(false);
        
        adicionarComponente(painelEdicoes, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    }
    
}
