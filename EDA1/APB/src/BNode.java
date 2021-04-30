public class BNode<E> {

    E element;
    BNode<E> left;
    BNode<E> right;


    BNode(){

        this(null, null, null);
    }
    BNode(E element){

        this.element = element;
    }

    BNode(E x, BNode<E> l, BNode<E> r){

        element = x;
        left = l;
        right = r;
    }

    void setLeft(BNode<E> x){

        left = x;
    }

    void setRight(BNode<E> x){

        right = x;
    }

    void setElement(E x){

        element = x;
    }

    BNode<E> getLeft(){

        return left;
    }

    BNode<E> getRight(){

        return right;
    }

    E getElement(){

        return element;
    }
}
