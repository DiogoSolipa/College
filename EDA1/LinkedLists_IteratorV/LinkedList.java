public class LinkedList<T> implements Iterable<T>{ 
    
    int size;
    SingleNode<T> Header;
    LinkedListIterator<T> list;
    
    public LinkedList(){          

        size=0;
        Header = new SingleNode<T>();
        list = new LinkedListIterator<T>(Header);

    } 
    
    public java.util.Iterator<T> iterator(){            
        
        return list;
    
    } 
    
    public int size(){    

       return size; 
    
    } 
    
    public boolean isEmpty(){    

        if (size==0){

            return true;

        }else{

            return false;

        }
    
    } 
    
    public SingleNode<T> header(){    

        
        return Header;

    }

    public void clear() {
        
        Header = new SingleNode<T>();
        list = new LinkedListIterator<T>(Header);
    
    }

    
    
    public void add(int i, T x){            

        SingleNode<T> Atual =new SingleNode<T>();
        
        size++;
        Atual=list.NodeAtual;
        for (int z=0;z<i;z++){      
            list.next();
            Atual=list.NodeAtual;
        }
        SingleNode<T> newNode = new SingleNode<T>(x,Atual.getNext());
        Atual.setNext(newNode);
        list.NodePrev=Atual;
        list.NodeAtual=newNode;

        list = new LinkedListIterator<T>(this.header());

    } 
    
    public void remove(int ind){            

        SingleNode<T> Atual =new SingleNode<T>();
        
        size--;
        for (int z=0;z<=ind;z++){      
            list.next();
            Atual=list.NodeAtual;
        }
        list.remove();
        list = new LinkedListIterator<T>(this.header());

    }     

    public void escrita(){

        System.out.print("[");
        while (list.hasNext()) {
            
            System.out.print(list.next());
            if (list.hasNext())
            System.out.print(", ");
        }
        System.out.println("]");

        list = new LinkedListIterator<T>(this.header());

    }
    
    public static void main(String[] args) {
        
        LinkedList<Integer> lista = new LinkedList<>();
        
        lista.add(0,2);

        lista.add(0,4);

        lista.add(1,3);

        lista.escrita();

        lista.remove(0);

        lista.escrita();

    }

}