public class QueueArray<E> implements Queue<E> {

    int start = 0;
    int end = 0;
    int dim;
    E queue[];

    QueueArray(){

        queue = (E[]) new Object[5];
    }
    QueueArray(int dim){
        this.dim = dim;
        queue = (E[]) new Object[dim];
    }

    public E front(){

        return queue[start];
    }

    public int size(){

        return end - start -1   ;
    }

    public boolean empty(){

        return size() == 0;
    }

    public E dequeue(){

        E t = queue[start];
        queue[start] = null;
        start++;
        return t;
    }

    public void enqueue(E o){

        queue[end] = o;
        end++;
    }

    public String toString(){

        String str ="";
        for (int i = start; i<= size() ; i++){

            str = str + queue[i];
        }
        return str;
    }


}
