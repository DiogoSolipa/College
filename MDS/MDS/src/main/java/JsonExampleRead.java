import java.io.FileReader; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JsonExampleRead  
{
    private static void parse(JSONObject user, LinkedList list) {
        String name = (String) user.get("nome");
        String card = (String) user.get("cartao");
        String role = (String) user.get("papel");

        User u = new User(name,card,role);

        list.list_insert(list,u);
    }

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception  
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha uma das seguintes opções para continuar");

        System.out.println("1 - Importar dados dos utilizadores");

        System.out.println("2 - Justificar Falta");

        System.out.println("3 - Mostrar Relatório de Faltas");

        System.out.println("4 - Consultar faltas por aluno");

        switch(sc.nextInt())
        {
            case 1: ;
            break;

            case 2: ;
            break;

            case 3: ;
            break;

            case 4: ;
            break;
        }



        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("dados.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;

            LinkedList list = new LinkedList();

            //String card = sc.nextLine();
             
            //Iterate over users array
            //userList.forEach( user -> parse((JSONObject) user, card, list));
            userList.forEach( user -> parse((JSONObject) user, list));

            //Print the list containing all the Users(Students+Professor)
            list.list_print(list);

            User user = list.first.user;

            ObjectMapper mapper = new ObjectMapper();
            

            FileWriter file = new FileWriter("test.json");
            file.write(user);
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
} 

