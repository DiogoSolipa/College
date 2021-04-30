public class BTree<E> {

    BNode<E> root;
    E element;

    BTree(){

        this(null);
    }

    BTree(E element){

        root = new BNode<E>(element);
    }

    BTree(E x, BNode<E>  l, BNode<E> r){

        root = new BNode<>(x, l, r);
    }

    boolean empty(){

        return root == null;
    }

    void postOrdem(BNode<E> root){

        if(root != null){

            postOrdem(root.getLeft());
            postOrdem(root.getRight());
            System.out.print(root.getElement() + " ");
        }
    }

    public void postOrdem(){

        postOrdem(root);
    }

    void preOrdem(BNode<E> root){

        if(root != null){

            System.out.print(root.getElement() + " ");
            preOrdem(root.getLeft());
            preOrdem(root.getRight());
        }
    }

    public void preOrdem(){

        preOrdem(root);
    }

    void emOrdem(BNode<E> root){

        if(root != null){

            emOrdem(root.getLeft());
            System.out.print(root.getElement() + " ");
            emOrdem(root.getRight());
        }
    }

    public void emOrdem(){

        emOrdem(root);
    }
}