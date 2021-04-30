
public class LinkedListLesson {

    Node first;
    int size;

    LinkedListLesson() {
        first = null;
        size = 0;
    }

    class Node {

        Lesson aula;
        Node next;

        Node(Lesson aula, LinkedListLesson listLesson) {

            this.aula = aula;
            next = listLesson.first;
        }

        public Node getNext() {

            return next;
        }

        public String toString() {

            String s = "";
            //this.aula.alunos.list_print(this.aula.alunos);
            return s + this.aula;
        }
    }


    public void list_insert(LinkedListLesson listLesson, Lesson aula) {
        Node node = new Node(aula, listLesson);

        listLesson.first = node;
        listLesson.size++;

        //return listLesson;
    }


    public void list_print(LinkedListLesson lessons) {

        Node node = lessons.first;

        while(node != null)
        {
            System.out.println(node.aula);
            node = node.next;
        }
    }


    //Search by Classe Date
    public Lesson list_find_by_date(LinkedListLesson list, String s)
    {
        Node node = list.first;

        while(node!= null)
        {
            if(node.aula.data.equals(s))
            {
                return node.aula;
            }
            node = node.next;
        }
        return null;
    }
}