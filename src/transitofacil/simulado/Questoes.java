package transitofacil.simulado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Random;
import transitofacil.excecoes.ArquivoException;

public class Questoes {

    public static void addQuestao(Questao questao, String arquivoAlvo) throws ArquivoException {
        ObjectInputStream ois;
        ObjectOutputStream oos;
        try {
            ArrayList<Questao> questoes = new ArrayList<Questao>();
            File f = new File(arquivoAlvo);
            if (f.exists() && !f.isDirectory()) {
                ois = new ObjectInputStream(new FileInputStream(arquivoAlvo));
                questoes = (ArrayList<Questao>) ois.readObject();
                ois.close();
            }
            
            //nao funciona, tem q arrumar
//            File arquivo = new File(questao.getImagem());
//            String[] tipoQuest = arquivoAlvo.split(".");
//            String[] tipoArq = questao.getImagem().split(".");
//            System.out.println(tipoQuest.length);
//            String novaImagem = tipoQuest[0] + "-" + Integer.toString(questoes.size() + 1) + tipoArq[1];
//            System.out.println(novaImagem);
//            Files.copy(arquivo.toPath(),
//                    (new File("img/" + novaImagem).toPath()),
//                    StandardCopyOption.REPLACE_EXISTING);
//
//            questao.setImagem(novaImagem);
            questoes.add(questao);

            oos = new ObjectOutputStream(new FileOutputStream(arquivoAlvo));
            oos.writeObject(questoes);
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new ArquivoException(arquivoAlvo);
        }
    }

    public static ArrayList<Questao> obterQuestoesAleatorias(String arquivoAlvo) throws ArquivoException {
        ObjectInputStream ois;
        ArrayList<Questao> retorno = new ArrayList<Questao>();
        try {
            ArrayList<Questao> questoes = new ArrayList<Questao>();
            File f = new File(arquivoAlvo);
            if (f.exists() && !f.isDirectory()) {
                ois = new ObjectInputStream(new FileInputStream(arquivoAlvo));
                questoes = (ArrayList<Questao>) ois.readObject();
                ois.close();
                //por padaro sao 20 questoes, caso mudar alterar o valor 20
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
