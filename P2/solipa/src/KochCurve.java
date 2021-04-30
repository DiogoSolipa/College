import java.util.*;
import java.lang.*;

public class KochCurve implements LSystem {
    Map<Character, String> rules;//dicionario criado para adicionar e conter regras

    public String starter;//variavel cirada para armazenar o valor inicial

    public KochCurve () { //construtor
        this.rules = new HashMap<Character, String>();//utilizaçao do dicionario para criar as regras de modo geral
    }

    public void setStart(String start) {
        this.starter = start;//para guardar o valor inicial
    }

    public void addRule(Character symbol, String word) {
        rules.put(symbol, word);//para aplicar as regras de modo geral
    }

        public String iter1 (String arg) {//recebe a string iterada 1 vez
        String ret = "";

        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            String val = rules.get(c);//verificar a string para procurar por chars que tenham uma regra aplicavel

            if (val != null){
                ret += val;//para adicionar a regra
            } else {
                ret += c;//copiar o simbolo se nao houver regra
            }

        }

        return ret;
    }

    public String iter(int n) {
        String ret = starter;//para ter uma string que vai sofrer alteraçoes e ser retornada

        for (int i = 0; i < n; i++) {
            rules.get(ret);//para obter a letra inicial e fazer as n iterações
            ret = iter1(ret);
        }

        return ret;
    }

}
