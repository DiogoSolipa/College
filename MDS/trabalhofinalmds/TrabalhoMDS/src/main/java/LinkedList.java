public class LinkedList {

    Node first;
    int size;

    LinkedList()
    {
        first = null;
        size = 0;
    }

    public class Node {

        User user;
        Node next;

        Node(User user, LinkedList list) {

            this.user = user;
            next = list.first;
        }

        public Node getNext() {

            return next;
        }

        public String toString() {

            String s = "";

            return s + this.user;
        }
    }


    public void list_insert(LinkedList list, User user)
    {
        Node node = new Node(user, list);

        list.first = node;
        list.size++;

        //return list;
    }


    public boolean list_find(LinkedList list, User user)
    {
        Node node = list.first;

        while(node != null)
        {
            if(node.user.card == user.card)
            {
                return true;
            }

            node = node.next;
        }

        return false;
    }


    public void list_print(LinkedList list)
    {
        Node node = list.first;

        for(int i = 0; i < size; i++)
        {
            System.out.println(node.user);

            node = node.next;
        }
    }


    //Search by Card Number
    public User list_find_by_card(LinkedList list, String s)
    {
        Node node = list.first;

        while(node!= null)
        {
            if(node.user.card.equals(s))
            {
                return node.user;
            }
            node = node.next;
        }
        return null;
    }

    public void list_abs_num_increase(LinkedList list, User u)
    {
        Node node = list.first;

        while(node != null)
        {
            if(node.user.card.equals(u.card))
            {
                node.user.abs_num++;
            }
            node = node.next;
        }
    }

    public void list_abs_num_decrease(LinkedList list, User u) {

        Node node = list.first;

        while (node != null) {
            if (node.user.card.equals(u.card)) {
                node.user.abs_num--;
            }
            node = node.next;
        }
    }
}