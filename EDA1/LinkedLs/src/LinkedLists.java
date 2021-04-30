public class LinkedLists<T> implements Iterable<T> {

    SingleNode<T> header;
    int size = 0;

    public LinkedLists(){

        header = new SingleNode<>();
    }

    public java.util.Iterator<T> iterator(){

        return iterator();
    }

    public int size(){

        return size;
    }

    public boolean isEmpty(){

        return size == 0;
    }

    public SingleNode<T> header(){

        return header;
    }

    void add(int i, T x){

        SingleNode<T> node = new SingleNode<>();
        SingleNode<T> newNode = new SingleNode<>();
        node = header;

        int count = 0;

        while(count < i) {

            node = node.getNext();
            count++;
        }

        newNode.setNext(node.getNext());
        node.setNext(newNode);

        newNode.setElement(x);
        size++;
    }

    void add(SingleNode<T> prev, T x){

        SingleNode<T> newNode;
        newNode = header.getNext();

        newNode.setNext(prev.getNext());
        prev.setNext(newNode);

        newNode.setElement(x);
        size++;
    }

    public void remove(int i){

        SingleNode<T> node;
        SingleNode<T> removeNode;
        int count = 0;
        node = header.getNext();
        removeNode = header;
        while(count < i){

            node = node.getNext();
            removeNode = removeNode.getNext();
            count++;
        }

        removeNode.setNext(node.getNext());
        size--;
    }

    SingleNode<T> findPrevious(T x){

        SingleNode<T> node;
        SingleNode<T> prev;
        node = header.getNext();
        prev = header;

        while(node.getElement() != x){

            prev = prev.getNext();
        }
        return prev;
    }

    public void remove(T x){

        SingleNode<T> node;
        SingleNode<T> removeNode;
        node = header.getNext();
        removeNode = header;

        while(node.getElement() != x){

            node = node.getNext();
            removeNode = removeNode.getNext();
        }
        removeNode.setNext(node.getNext());
    }

    public String toString(){

        SingleNode<T> node;
        String s = "";
        node = header.getNext();
        while(node != null){

            s = s + node + "|";
            node = node.getNext();
        }

        return s;
    }

    public void set(int i, T x){

        SingleNode<T> node;
        node = header.getNext();
        int count = 0;

        while(count < i){

            node = node.getNext();
            count++;
        }

        node.setElement(x);
    }

    public T get(int i){

        SingleNode<T> node;
        node = header.getNext();
        int count = 0;

        while(count < i){

            node = node.getNext();
            count++;
        }

        return node.getElement();
    }


    SingleNode<T> getNode(int i){

        SingleNode<T> node;
        node = header.getNext();
        int count = 0;

        while(count < i){

            node = node.getNext();
            count++;
        }

        return node;
    }
}
