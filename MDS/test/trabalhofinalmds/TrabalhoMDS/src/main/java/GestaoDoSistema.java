import java.util.Scanner;

public class GestaoDoSistema {
    LeitorDeCartoes l;

    GestaoDoSistema(LeitorDeCartoes l) {
        this.l = l;
    }

    LeitorDeCartoes getL() {
        return this.l;
    }

    public static void justifyAbsence(LeitorDeCartoes l, String card)
    {
        User user = l.users.list_find_by_card(l.users, card);

        if(l.users.list_find(l.users, user))
        {
            l.users.list_abs_num_decrease(l.users, user);
        }
    }

    public static User checkStudentAbsence(LeitorDeCartoes l, String card)
    {
        User user = l.users.list_find_by_card(l.users,card);
        return user;
    }



    public static void parseInput(LeitorDeCartoes l, int i, GestaoDoSistema g)
    {
        Scanner sc = new Scanner(System.in);
        String card = "";

        switch(i)
        {
            case 1: l.UserDataReadFile(l);
                    l.LessonDataReadFile(l);
                    l.lessonsUserInsert(l);
                break;

            case 2: card = sc.nextLine();
                    g.justifyAbsence(l, card);
                break;

            case 3: l.users.list_print(l.users);
                break;

            case 4:
                    card = sc.nextLine();
                    System.out.println(g.checkStudentAbsence(l, card).name +  "\nNumero de Faltas: " + g.checkStudentAbsence(l, card).abs_num);
                break;
        }
    }
}
