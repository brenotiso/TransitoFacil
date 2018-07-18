/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transitofacil.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author rodrigo
 */
public class TelaTransitoGeral extends Tela {
    private static TelaTransitoGeral instancia = null;
    private JLabel lbTitulo;
    private JButton btLegislacao;
    private JButton btDirecaoDefensiva;
    private JButton btPrimeirosSocorros;
    private JButton btMecanica;
    private JButton btVoltar;
    
    public static TelaTransitoGeral getInstance(){
        if(instancia == null){
            instancia = new TelaTransitoGeral();
        }
        return instancia;
    }
    
    private TelaTransitoGeral() {
        super("Trânsito Facil - Trânsito Geral");
        construirTela();
    }
    
    private void construirTela(){
        lbTitulo = new JLabel("Trânsito Geral");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));
        
        btLegislacao = new JButton("Legislação de trânsito");
        btDirecaoDefensiva = new JButton("Direção Defensiva");
        btPrimeirosSocorros = new JButton("Primeiros Socorros");
        btMecanica = new JButton("Mecânica");
        btVoltar = new JButton("< Voltar");
        
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chamarOutraJanela(TelaPrincipal.getInstance());
            }
        });
        
        adicionarComponente(lbTitulo, GridBagConstraints.PAGE_START, GridBagConstraints.NONE,
                0, 0, 2, 1, 3, 3, 3, 3, 0.0, 0.5, 0, 0);
        adicionarComponente(btLegislacao, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                1, 0, 1, 1, 3, 140, 3, 3, 0.5, 0.5, 27, 50);
        adicionarComponente(btDirecaoDefensiva, GridBagConstraints.LINE_END, GridBagConstraints.NONE,
                1, 1, 1, 1, 3, 3, 3, 140, 0.5, 0.5, 51, 50);
        adicionarComponente(btPrimeirosSocorros, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                2, 0, 1, 1, 3, 140, 3, 3, 0.5, 0.5, 80, 50);
        adicionarComponente(btMecanica, GridBagConstraints.LINE_END, GridBagConstraints.NONE,
                2, 1, 1, 1, 3, 3, 3, 140, 0.5, 0.5, 0, 50);
        adicionarComponente(btVoltar, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                3, 0, 2, 1, 3, 3, 0, 3, 0.5, 0.5, 0, 50);
        //adicionarComponente(btVoltar, GridBagConstraints.LAST_LINE_END, GridBagConstraints.NONE,
                //4, 1, 1, 1, 0, 0, 3, 3, 0.5, 0.5, 0, 0);
    }
    
    
    
}
