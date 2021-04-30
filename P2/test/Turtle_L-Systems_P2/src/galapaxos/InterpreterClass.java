package galapaxos;

import java.util.List;

public class InterpreterClass implements Interpreter {

    Turtle turtle = new Turtle(Turtle.NO_DEFAULT_WINDOW); // para so ter 1 janela aberta
    TurtleDrawingWindow turtleDraw = new TurtleDrawingWindow();
    InterpreterClass(){
       //turtleDraw.setOrigin(230,150);
       turtle.speed(6000); // de modo a ser instantaneo a construção do gráfico
       turtle.penSize(1);

       turtleDraw.setGrid(false);
       turtleDraw.add(turtle);
       turtleDraw.setVisible(true);
       turtle.hide();

    }
    public void run(List<TurtleStatement> program){
        for(int i = 0; i<program.size();i++) {
                TurtleStatement t = program.get(i);
                t.run(this); // vai dar "run" nesta class, dai o (this)

        }
    }

    public void runForward(Forward statement){
        turtle.move(statement.getDistance());

    }

    public void runTurn(Turn statement){
        turtle.turn(statement.getRotation());

    }

    public void runPenUp(PenUp statement){
        turtle.penUp();

    }

    public void runPenDown(PenDown statement){
        turtle.penDown();
    }
}
