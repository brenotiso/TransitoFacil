package transitofacil.excecoes;

public class ArquivoException extends Exception {
    
    public ArquivoException(String arq) {
        super("Erro de escrita no arquivo: " + arq); 
    }
}

