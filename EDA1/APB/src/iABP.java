public interface iABP <E extends Comparable<? super E>> {

    public boolean isEmpty();
    public boolean contains(E x);
    public E findMin();
    public E findMax();
    public void insert(E x);
    public void remove(E X);
    public E find(E x);
    public void printInOrder();
    public void printPostOrder();
    public void printPreOrder();
}
