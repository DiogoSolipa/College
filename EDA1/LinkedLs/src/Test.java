import javax.sound.sampled.Line;

public class Test {

    public static void main (String args []) {


       LinkedLists<Integer> list = new LinkedLists<>();
       SingleNode<Integer> node = new SingleNode<>(1);

       list.add(0,1);
       list.add(1,2);
       list.add(2,3);


        System.out.println(list.get(2));

























        /*SingleNode<Integer> s = new SingleNode<>(1, new SingleNode(3));
        SingleNode<Integer> s2 = new SingleNode<>(2);
        SingleNode<Integer> s3 = new SingleNode<>(3);
        SingleNode<Integer> s4 = new SingleNode<>(4);

        SingleNode ele = s;

        s.setNext(s2);
        s2.setNext(s3);
        s3.setNext(s4);



        while(ele != null){
            System.out.println(ele);
            ele = ele.getNext();

        }*/


    }

}
