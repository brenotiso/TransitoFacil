package transitofacil.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

        pnResultados = new JPanel(new GridLayout(0, 1));
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);
        modelo.addColumn("Questao");
        modelo.addColumn("Resultado");
        int i = 1;
        for(Boolean b : acertos){
            String result;
            if(b){
                result = "Acertou";
            }else{
                result = "Errou";
            }
            modelo.addRow(new Object[]{i, result});
            i++;
        }
        barraRolagem = new JScrollPane(tabela);
        pnResultados.add(barraRolagem);
        
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
