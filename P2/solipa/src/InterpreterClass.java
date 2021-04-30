import java.util.*;

import galapagos.*;

public class InterpreterClass implements Interpreter {
    public Turtle turtle = new Turtle();//criar uma turtle nova

    public void run(List<TurtleStatement> program) {

    }

    public void runForward(Forward statement) {
        turtle.move(statement.getDistance());
    }

    public void runTurn(Turn statement) {
        turtle.turn(statement.getDegree());
    }

    public void runPenUp(PenUp statement) {
        turtle.penUp();
    }

    public void runPenDown(PenDown statement) {
        turtle.penDown();
    }
}
