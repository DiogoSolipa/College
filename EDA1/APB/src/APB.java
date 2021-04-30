public class APB <E extends Comparable<? super E>> implements iABP<E> {

    NodeAPB<E> root;

    static class NodeAPB<E> {

        E element;
        NodeAPB<E> left;
        NodeAPB<E> right;

        NodeAPB(E e) {

            element = e;
            left = null;
            right = null;
        }

        NodeAPB(E element, NodeAPB<E> left, NodeAPB<E> right) {

            this.element = element; 
            this.left = left;
            this.right = right;
        }
    }

    public int height(){

        return height(root);
    }

    public int height(NodeAPB<E> node){

        int y = 0;
        int x = 0;

        if (node == null)
            return -1;

        else
            x = 1 + height(node.right);
            y = 1 + height(node.left);

        if ( x > y)
            return x;
        else
            return y;
    }

    public boolean isEmpty() {

        return root == null;
    }

    public E find(E x){

        return find(x, root);
    }

    private E find(E x, NodeAPB<E> node){

        if(x == null){

            return null;
        }
        else if(node.element.compareTo(x) < 0)

            return find(x, node.right);

        else if(node.element.compareTo(x) > 0)

            return find(x, node.left);

        return node.element;
    }

    public E findMin() {

        if (isEmpty())

            return null;


        return findMin(root);
    }

    private E findMin(NodeAPB<E> node) {

        if (node.left == null)

            return node.element;

        return findMin(node.left);
    }

    public E findMax() {

        if (isEmpty())

            return null;

        return findMax(root);
    }

    private E findMax(NodeAPB<E> node) {

        if (node.right == null)

            return node.element;

        return findMax(node.right);
    }


    public boolean contains(E x) {

          if (isEmpty())

            return false;

        return contains(x, root);
    }


    private boolean contains(E x, NodeAPB<E> node) {

        if (x == null)

            return false;

        else {

            if (node.element.compareTo(x) > 0)

                return contains(x, node.left);

            else if (node.element.compareTo(x) < 0)

                return contains(x, node.right);

            else
                return true;
        }
    }

    public void insert(E x) {

        root = insert(x, root);
    }

    private NodeAPB<E> insert(E x, NodeAPB<E> node) {

        if (node == null)

            node = new NodeAPB<E>(x, null, null);

        else if (node.element.compareTo(x) > 0)

            node.left = insert(x, node.left);

        else if (node.element.compareTo(x) < 0)

            node.right = insert(x, node.right);


        return node;
    }

    public void remove(E x) {

        root = remove(x, root);

    }

    private NodeAPB<E> remove(E x, NodeAPB<E> node) {

        if (node == null)

            node = new NodeAPB<E>(x, null, null);


        else if (node.element.compareTo(x) < 0)

            node.right = remove(x, node.right);

        else if (node.element.compareTo(x) > 0)

            node.left = remove(x, node.left);

        else if (node.left != null && node.right != null){

            E min = findMin(node.right);
            node.element = min;
            node.right = remove(min, node.right);
        }

        else if(node.left == null)

            node = node.right;

        else
            node = node.left;


        return node;
    }

    public void printInOrder(){

        printInOrder(root);
    }

    private void printInOrder(NodeAPB<E> node){

        if(node != null){

            printInOrder(node.left);
            System.out.println(node.element + " ");
            printInOrder(node.right);
        }
    }

    public void printPreOrder(){

        printPreOrder(root);
    }

    private void printPreOrder(NodeAPB<E> node){

        System.out.println(node.element + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printPostOrder(){

        printPostOrder(root);
    }

    private void printPostOrder(NodeAPB<E> node){

        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.println(node.element + " ");
    }
}
