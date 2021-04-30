import org.w3c.dom.Node;

public class AVL <E extends Comparable<? super E>> implements iABP<E> {
    
    NodeAVL<E> root;

    static class NodeAVL<E> {

        E element;
        NodeAVL<E> left;
        NodeAVL<E> right;
        int height;

        NodeAVL(){

            this(null,null, null);
        }

        NodeAVL(E x){

            element = x;
        }

        NodeAVL(E x, NodeAVL<E> left, NodeAVL<E> right){

            element = x;
            this.left = left;
            this.right = right;
            height = 0;
        }
    }

    private NodeAVL<E> balance (NodeAVL<E> node){

        if (node == null)

            return node;

        if (height(node.left) - height(node.right) > 1)

            if (height(node.left.left) >= height(node.left.right))

                node = rotateL(node);
            else

                node = rLeftRight(node);
        else

        if (height(node.right) - height(node.left) > 1)

            if (height(node.right.right) >= height(node.right.left))

                node = rotateR(node);
            else

                node = rRightLeft(node);

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

    private NodeAVL<E> rLeftRight(NodeAVL<E> node){

        System.out.println("Rotacao dupla left right");

        node.left = rotateL(node.left);

        return rotateR(node);
    }

    private NodeAVL<E> rRightLeft(NodeAVL<E> node){

        System.out.println("Rotacao dupla right left");

        node.right = rotateR(node.right);

        return rotateL(node);
    }

    private NodeAVL<E> rotateR(NodeAVL<E> node){


        System.out.println("Rotation left");
                
        NodeAVL<E> node1 = node.right;
        node.right = node1.left;
        node1.left = node;

        node.height = Math.max(height(node.left),height(node.right)) + 1;
        node1.height = Math.max(height(node1.right),height(node1.left)) + 1;

        return node1;
    }

    private NodeAVL<E> rotateL(NodeAVL<E> node){


        System.out.println("Rotation right");

        NodeAVL<E> node1 = node.left;
        node.left = node1.right;
        node1.right = node;

        node.height = Math.max(height(node.left),height(node.right)) + 1;
        node1.height = Math.max(height(node1.right),height(node1.left)) + 1;


        return node1;
    }

    public int height(){

        return height(root);
    }

    private int height(NodeAVL<E> node){


        if (node == null)
            return -1;

        return node.height;
    }


    public boolean isEmpty() {

        return root == null;
    }

    public E find(E x){

        return find(x, root);
    }

    private E find(E x, NodeAVL<E> node){

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

    private E findMin(NodeAVL<E> node) {

        if (node.left == null)

            return node.element;

        return findMin(node.left);
    }

    public E findMax() {

        if (isEmpty())

            return null;

        return findMax(root);
    }

    private E findMax(NodeAVL<E> node) {

        if (node.right == null)

            return node.element;

        return findMax(node.right);
    }


    public boolean contains(E x) {

        if (isEmpty())

            return false;

        return contains(x, root);
    }


    private boolean contains(E x, NodeAVL<E> node) {

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

    private NodeAVL<E> insert(E x, NodeAVL<E> node) {

        if (node == null)

            node = new NodeAVL<E>(x, null, null);

        else if (node.element.compareTo(x) > 0)

            node.left = insert(x, node.left);

        else if (node.element.compareTo(x) < 0)

            node.right = insert(x, node.right);

        else
            ;


        return balance(node);
    }

    public void remove(E x) {

        root = remove(x, root);

    }

    private NodeAVL<E> remove(E x, NodeAVL<E> node) {

        if (node == null)

            node = new NodeAVL<E>(x, null, null);


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


        return balance(node);
    }

    public void printInOrder(){

        printInOrder(root);
    }

    private void printInOrder(NodeAVL<E> node){

        if(node != null){

            printInOrder(node.left);
            System.out.println(node.element + " ");
            printInOrder(node.right);
        }
    }

    public void printPreOrder(){

        printPreOrder(root);
    }

    private void printPreOrder(NodeAVL<E> node){

        System.out.println(node.element + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printPostOrder(){

        printPostOrder(root);
    }

    private void printPostOrder(NodeAVL<E> node){

        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.println(node.element + " ");
    }

    public static void main (String args []){

        AVL<Integer> avl = new AVL<>();
        
        avl.insert(10);
        avl.insert(8);
        avl.insert(6);
        avl.insert(5);


        System.out.println(avl.height());
        System.out.println("---------------");
        avl.printInOrder();

        avl.remove(10);

        avl.printInOrder();

        System.out.println("---------");
        System.out.println(avl.height());
    }
}
