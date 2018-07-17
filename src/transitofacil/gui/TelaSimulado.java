package transitofacil.gui;

import transitofacil.simulado.*;

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
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import transitofacil.excecoes.ArquivoException;

public class TelaSimulado extends Tela {
    private String tipoSimulado;

    private JLabel lbTitulo;

    private JTabbedPane jtpTabs;
    private ArrayList<ButtonGroup> alGruposRespostas;
    private ArrayList<ButtonModel> alRespostasCorretas;

    private JButton btVoltar;
    private JButton btTerminar;

    private Thread tTerminarSimulado;

    public TelaSimulado(String tipoSimulado) {
        super("Trânsito Fácil - Simulado");

        this.tipoSimulado = tipoSimulado;

        try { //verificar essa parte!!
            construirTela();
        } catch (ArquivoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            chamarOutraJanela(TelaPrincipal.getInstance());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            chamarOutraJanela(TelaPrincipal.getInstance());

        }
    }

    private void construirTela() throws ArquivoException, IOException {
        lbTitulo = new JLabel("Simulado");
        lbTitulo.setFont(new Font("", Font.BOLD, 28));

        btTerminar = new JButton("Terminar");
        btTerminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int i = 0;
                ArrayList<Boolean> acertos = new ArrayList<Boolean>();
                for (ButtonGroup b : alGruposRespostas) {
                    acertos.add(b.getSelection().equals(alRespostasCorretas.get(i)));
                    i++;
                }
                chamarOutraJanela(new TelaSimuladoConcluido(acertos));
            }
        });

        jtpTabs = new JTabbedPane();

        File f = new File(tipoSimulado + ".bin");
        if (f.exists() && !f.isDirectory()) {

            alGruposRespostas = new ArrayList<>();
            alRespostasCorretas = new ArrayList<>();
            ArrayList<Questao> questoes = Questoes.obterQuestoesAleatorias(tipoSimulado + ".bin");
            int i = 1;
            for (Questao q : questoes) {
                JPanel jp = new JPanel(new GridLayout(2, 1));
                jp.add(new JLabel(q.getPergunta()));

                ArrayList<String> alternativas = q.getAlternativas();
                JRadioButton alternativa1 = new JRadioButton(alternativas.get(0));
                JRadioButton alternativa2 = new JRadioButton(alternativas.get(1));
                JRadioButton alternativa3 = new JRadioButton(alternativas.get(2));
                JRadioButton alternativa4 = new JRadioButton(alternativas.get(3));
                ArrayList<JRadioButton> radioAlternativas = new ArrayList<>();
                radioAlternativas.add(alternativa1);
                radioAlternativas.add(alternativa2);
                radioAlternativas.add(alternativa3);
                radioAlternativas.add(alternativa4);

                ButtonGroup group = new ButtonGroup();
                group.add(alternativa1);
                group.add(alternativa2);
                group.add(alternativa3);
                group.add(alternativa4);

                alGruposRespostas.add(group);
                alRespostasCorretas.add(radioAlternativas.get(q.getAlternativaCorreta() - 1).getModel());

                JPanel radioPanel = new JPanel(new GridLayout(4, 1));
                radioPanel.add(alternativa1);
                radioPanel.add(alternativa2);
                radioPanel.add(alternativa3);
                radioPanel.add(alternativa4);

                JPanel jpInferior = new JPanel(new GridLayout(1, 2));
                jpInferior.add(radioPanel);

                System.out.println();
                //Adicionando a imagem
                if (!q.getImagem().equals("")) {
                    String IMG_PATH = "placas/" + q.getImagem(); //verificar essa linha!!!
                    BufferedImage img = ImageIO.read(new File(IMG_PATH));
                    JLabel lbImagem = new JLabel(new ImageIcon(img));
                    jpInferior.add(lbImagem);
                }

                jp.add(jpInferior);
                jtpTabs.addTab(Integer.toString(i), jp);
                i++;
            }

            tTerminarSimulado = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (verificaGupos()) {
                            adicionarComponente(btTerminar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 1, 1, 1, 0, 0, 0, 0);
                            revalidate();
                            repaint();
                            return;
                        }
                    }
                }

                private boolean verificaGupos() {
                    for (ButtonGroup b : alGruposRespostas) {
                        if ((b.getSelection() == null)) {
                            return false;
                        }
                    }
                    return true;
                }
            });
            tTerminarSimulado.start();

            jtpTabs.setPreferredSize(new Dimension(650, 430));
            // Adicionando os componentes à tela
            adicionarComponente(jtpTabs, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1, 0, 0, 0, 0);

        } else {
            //arrumar futuramente
            btVoltar = new JButton("Voltar");
            btVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    chamarOutraJanela(TelaPrincipal.getInstance());
                }
            });
            JLabel nada = new JLabel("Nenhuma pergunta cadastrada!");
            adicionarComponente(nada, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1, 1, 1, 1, 0, 0, 0, 0);
            adicionarComponente(btVoltar, GridBagConstraints.CENTER, GridBagConstraints.NONE, 2, 1, 1, 1, 0, 0, 0, 0);
        }

        adicionarComponente(lbTitulo, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                0, 0, 0, 1, 0, 0, 0, 0);
    }
}
