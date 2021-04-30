import java.util.*;
public class SingleNode<T> {

    T current;
    SingleNode<T> ref;

    SingleNode() {
        this(null);
    }

    SingleNode(T x) {

        current = x;
        ref = null;
    }

    SingleNode(T x, SingleNode y) {
        current = x;
        ref = y;
    }

    public T getElement() throws InvalidNodeException {

        if (current == null)
            throw new InvalidNodeException();

        return current;
    }


    public SingleNode<T> getNext(){

        return ref;
    }

    public void setElement(T x){

        current = x;
    }

    public void setNext(SingleNode<T> y){

        ref = y;
    }

    public String toString(){

        String s ="";

        return s + current;
    }
}
