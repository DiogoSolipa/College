public class ArrayStack <E> implements Stack <E> {

    E[] stack;

    int dim;
    int top = 0;

    ArrayStack(){

        stack = (E[]) new Object[5];
    }
    ArrayStack(int dim){
        this.dim = dim;
        stack = (E[]) new Object[dim];
    }

    public int size(){

        return stack.length;
    }

    public boolean empty(){

        return size() == 0;
    }

    public void push(E o) {

        stack[top] = o;
        top++;
    }

    public E top(){

        return stack[top - 1];
    }

    public E pop(){

        return stack[top--];
    }

    public String toString(){

        String print="";

        for(int i = 0; i< top ; i++){

            print = print + stack[i] + "\n";
        }
        return print;
    }


    /*public boolean palindrome(String txt){


        ArrayStack<Character> str = new ArrayStack<>(txt.length());

        for (int i = 0; i< txt.length(); i++){

            str.push((char)txt[i]);


        }
    }*/
}
