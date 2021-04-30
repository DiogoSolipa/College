import java.util.List;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class Test
{
    public static void main(String[] args) throws Exception
    {   
        /*EX 01
        byte [] bts = new byte [10];

        try 
        {
            int n = System.in.read(bts);

            String s = new String(bts, 0, n - System.lineSeparator().length());
            System.out.println("Lido: " + s);

            int valor = Integer.parseInt(s);

            System.out.println("Introduzido " + valor + "\nResultado: " + (valor+1));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/

        /*EX 02
        if (args.length > 0)
        {
            try
            {
                int valor = Integer.parseInt(args[0]);
                System.out.println("recebido " + valor);
            }
            catch(InputMismatchException i)
            {
                System.out.println("boom!");
            }

        }
        else
        {
            System.out.println("Sem argumentos");
        }*/

        List<String> list = new LinkedList<>();

        if (args.length > 0)
        {
            System.out.println("Inicial: " + args);
            
            for (String s : args)
            {
                orderedInsertion(s, list);
            }

            System.out.println("Final: " + list);

        }

        System.exit(0);
    }

    public static void orderedInsertion(String s, List<String> ordered)
    {
        int pos = 0;
        while(pos < ordered.size() && ordered.get(pos).compareTo(s) < 0)
        {
            pos++;
        }
        ordered.add(pos, s);
    }
}