public class AgendaAVL {

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



    public static void main (String args []){


        AgendaAVL agenda = new AgendaAVL();


        agenda.insert(new Contact("diogo", 912681160));
        agenda.insert(new Contact("manel", 912681161));
        agenda.insert(new Contact("abel", 912681162));
        agenda.insert(new Contact("jose", 912681163));
        agenda.insert(new Contact("gilipe", 912681164));
        agenda.insert(new Contact("tbel", 912681162));
        agenda.insert(new Contact("uose", 912681163));
        agenda.insert(new Contact("yilipe", 912681164));

        System.out.println("-----------------");
        agenda.height();

        agenda.list();
        agenda.remove("jose");
        System.out.println("-----------------");
        agenda.list();
        System.out.println("-----------------");
        agenda.find("yilipe");
        //agenda.findNumber(912681162);
    }
}
