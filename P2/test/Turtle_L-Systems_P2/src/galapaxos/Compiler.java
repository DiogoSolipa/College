package galapaxos;

import java.util.HashMap;
import java.util.Vector;

public class Compiler {
    HashMap<Character, TurtleStatement> charToAction; //declaração do mapa

    public Compiler(){
        charToAction = new HashMap<Character, TurtleStatement>(); //inicialização do mapa

    }
    public void addRule(Character letter, TurtleStatement statement) { // caracter para o Turtle Statement
            charToAction.put(letter, statement);

    }
    protected TurtleStatement compile(Character c) { // funçao que vai buscar ao mapa o valor referente ao caracter nos parametros
       return charToAction.get(c);

    }
    protected Vector<TurtleStatement> compile(String word) {
        Vector<TurtleStatement> result = new Vector<>();
        for (int i = 0; i < word.length(); i++) {
            result.add(compile(word.charAt(i)));
        }
        return result;
    }
}