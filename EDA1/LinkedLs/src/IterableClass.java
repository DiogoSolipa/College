public class IterableClass<T> implements java.util.Iterator<T> {

    SingleNode<T> node;


    public boolean hasNext(){

        return node != null;
    }

    public T next(){

        T x = null;

        if(!hasNext()){
            return null;
        }
        else
            x = node.getElement();
            node = node.getNext();

        return x;
    }

    public void remove(){

        throw new UnsupportedOperationException("Não implementado");

    }
}
