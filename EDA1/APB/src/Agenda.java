public class Agenda {

    public static class Contact implements Comparable<Contact> {

        int number;
        String name;

        Contact(){
            this(null, 0);
        }

        Contact(String name){

            this.name = name;
        }

        Contact(int number){

            this.number = number;
        }

        Contact(String name, int number){

            this.number = number;
            this.name = name;
        }

        public String toString(){

            return name + "---->" + number;
        }


        public int compareTo(Contact x) {

            return name.compareTo(x.name);
        }
    }

        //APB<Contact> agenda = new APB<>();

        AVL<Contact> agenda = new AVL<>();

        public void insert(Contact x){

            agenda.insert(x);
        }

        public void remove(String name){

            agenda.remove(new Contact(name));
        }

        public void list(){
            agenda.printInOrder();
        }

        public void find(String name){

             System.out.println(agenda.find(new Contact(name)).number);
        }

        public void height(){

            System.out.println(agenda.height());
        }


        /*public void findNumber(int n){

            findNumber(agenda.root, n);
        }

        private void findNumber(APB.NodeAPB<Contact> node, int n){ //implementar iterador

            if(node != null){
                if(node.element.number == n){

                    System.out.println(node.element.name);
                }
                findNumber(node.left, n);
                findNumber(node.right, n);
            }
        }*/


        /*private void isBalanced(){

            System.out.println(agenda.isBalanced());
        }*/



        public static void main (String args []){


            /*Agenda agenda = new Agenda();


            agenda.insert(new Contact("diogo", 912681160));
            agenda.insert(new Contact("manel", 912681161));
            agenda.insert(new Contact("abel", 912681162));
            //agenda.insert(new Contact("jose", 912681163));
            //agenda.insert(new Contact("filipe", 912681164));

            agenda.height();

            agenda.list();
            //agenda.remove("jose");
            System.out.println("-----------------");
            agenda.list();
            System.out.println("-----------------");
            //agenda.find("filipe");
            //agenda.findNumber(912681162);*/


            Agenda agenda = new Agenda();

            agenda.insert(new Contact("diogo", 912681160));
            agenda.insert(new Contact("manel", 912681161));
            agenda.insert(new Contact("abel", 912681162));

            //agenda.isBalanced();

        }
}