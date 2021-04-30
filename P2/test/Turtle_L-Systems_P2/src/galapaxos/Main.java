package galapaxos;



import java.util.Vector;


public class Main  {

    public static void main(String [] args){
        KochCurve charMult = new KochCurve();
        Compiler comp = new Compiler();
        InterpreterClass it = new InterpreterClass();


       /* Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
*/


        charMult.setStart("F-G-G");
        charMult.addRule('G', "GG");
        charMult.addRule('F', "F-G+F+G−F");


        String word = charMult.loopIter(4);

        comp.addRule('F', new Forward(20));
        comp.addRule('G', new Forward(20));
        comp.addRule('+', new Turn(120));
        comp.addRule('-', new Turn(-120));

        Vector<TurtleStatement> vec = comp.compile(word);
        it.run(vec);



    }


}

