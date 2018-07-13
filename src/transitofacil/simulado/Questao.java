package transitofacil.simulado;

import java.io.Serializable;
import java.util.ArrayList;

public class Questao implements Serializable{
    private final String pergunta;
    private final String imagem;
    private final ArrayList<String> alternativas;
    private final int alternativaCorreta;

    public Questao(String pergunta, String imagem, ArrayList<String> alternativas, int alternativaCorreta) {
        this.pergunta = pergunta;
        this.imagem = imagem;
        this.alternativas = alternativas;
        this.alternativaCorreta = alternativaCorreta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getImagem() {
        return imagem;
    }

    public ArrayList<String> getAlternativas() {
        return alternativas;
    }

    public int getAlternativaCorreta() {
        return alternativaCorreta;
    }
    
}
