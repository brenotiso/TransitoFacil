package transitofacil.simulado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import transitofacil.excecoes.ArquivoException;

public class Questoes {

    public static void addQuestao(Questao questao, int tipo) throws ArquivoException {
        ObjectInputStream ois;
        ObjectOutputStream oos;
        String arquivo = System.getProperty("user.dir");
        try {
            if(tipo == 0){
                arquivo += "/placas.bin";
            }else{
                arquivo += "/geral.bin";
            }
            
            File f = new File(arquivo);
            ArrayList<Questao> questoes = new ArrayList<Questao>();
            if (f.exists() && !f.isDirectory()) {
                ois = new ObjectInputStream(new FileInputStream(arquivo));
                questoes = (ArrayList<Questao>) ois.readObject();
                ois.close();
            }
            
            questoes.add(questao);
            
            oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(questoes);
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new ArquivoException(arquivo);
        }
    }

    public static ArrayList<Questao> obterQuestoesAleatorias(String arquivoAlvo) throws ArquivoException {
        ObjectInputStream ois;
        ArrayList<Questao> retorno = new ArrayList<Questao>();
        try {
            ArrayList<Questao> questoes = new ArrayList<Questao>();
            File f = new File(arquivoAlvo);
            if (f.exists() && !f.isDirectory()) {
                ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/" +arquivoAlvo));
                questoes = (ArrayList<Questao>) ois.readObject();
                ois.close();
                //por padrao sao 20 questoes, caso mudar alterar o valor 20
                if (questoes.size() > 20) {
                    Random rand = new Random();
                    for (int i = 0; i < 20; i++) {
                        int randomIndex = rand.nextInt(questoes.size());
                        retorno.add(questoes.get(randomIndex));
                        questoes.remove(randomIndex);
                    }
                } else {
                    retorno = questoes;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new ArquivoException(arquivoAlvo);
        }
        return retorno;
    }

}
