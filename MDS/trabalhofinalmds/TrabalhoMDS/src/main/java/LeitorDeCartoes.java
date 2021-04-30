import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class LeitorDeCartoes {

    //Vars da classe
    LinkedListLesson aulas;     //LinkedList para guardar as informações das aulas
    LinkedList users;          //LinkedList para guardar todos os alunos existentes


    //Construtor da Classe
    LeitorDeCartoes() {
        this.aulas = new LinkedListLesson();
        this.users = new LinkedList();
    }

    LinkedList getUsers() {
        return this.users;
    }

    LinkedListLesson getAulas() {
        return this.aulas;
    }


    //Método que insere lê o ficheiro: dados.json e insere os dados numa LinkedList de tipo User
    public static void UserDataReadFile(LeitorDeCartoes l) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("dados.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            //Iterate over users array
            userList.forEach(user -> parseUserObjectForUsers((JSONObject) user, l.users));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Método que insere lê o ficheiro: aulas.json e insere a informação das aulas numa LinkedListLesson
    public static void LessonDataReadFile(LeitorDeCartoes l) {
        JSONParser jsonParser2 = new JSONParser();

        try (FileReader reader2 = new FileReader("aulas.json")) {
            //Read JSON file
            Object obj2 = jsonParser2.parse(reader2);

            JSONArray userList2 = (JSONArray) obj2;

            //Iterate over users array
            userList2.forEach(user -> parseUserObjectForLessons((JSONObject) user, l.aulas));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //Método que vai colocar todos os alunos e professor existentes na linked list users
    public static void parseUserObjectForUsers(JSONObject user, LinkedList users) {
        String name = (String) user.get("nome");
        String card = (String) user.get("cartao");
        String role = (String) user.get("papel");

        User u = new User(name, card, role);
        users.list_insert(users, u);
    }

    //Método que vai colocar todas as aulas existentes na linkedlist de aulas
    public static void parseUserObjectForLessons(JSONObject user, LinkedListLesson aulas) {
        String data = (String) user.get("data");
        String hora = (String) user.get("hora");

        Lesson l = new Lesson(data, hora);
        aulas.list_insert(aulas, l);
    }


    public void lessonsUserInsert(LeitorDeCartoes l) {
        LinkedListLesson.Node aulas = l.aulas.first;

        while (aulas != null) {
            Scanner sc = new Scanner(System.in);
            String cod = sc.next();

            if (l.users.list_find_by_card(l.users, cod) != null) {
                aulas.aula.presencas.list_insert(aulas.aula.presencas, l.users.list_find_by_card(l.users, cod));
            } else if (cod.equals("next")) {
                LinkedList.Node node = l.users.first;

                if(aulas.aula.presencas.list_find_by_card(aulas.aula.presencas, "100") == null)
                {
                    aulas = aulas.next;
                }
                else
                {
                    for (int i = 0; i < l.users.size; i++) {
                        User u = node.user;

                        if (!aulas.aula.presencas.list_find(aulas.aula.presencas, u) && !aulas.aula.faltas.list_find(aulas.aula.faltas, u)) {
                            l.users.list_abs_num_increase(l.users, u);
                            aulas.aula.faltas.list_insert(aulas.aula.faltas, u);
                        }
                        aulas.aula.nFaltas = l.users.size - aulas.aula.presencas.size;
                        node = node.next;
                    }

                    if (aulas.next == null) {
                        break;
                    }
                    aulas = aulas.next;
                }
            }
        }
    }
}
