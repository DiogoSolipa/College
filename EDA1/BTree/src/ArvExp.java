import java.util.StringTokenizer;


public class ArvExp
{
    private static boolean operator(String s)
    {
        return ((s.length()==1) && (s == "+") || (s == "-") || (s == "*") || (s == "/"));
    }

    private static boolean isNum(String s)
    {
        try
        {
            float f=Float.valueOf(s).floatValue();
            return true;
        }
        catch (Exception e)
        {

        }
        return false;
    }

    public static float avalia(BTree<String> x)
    {
        return avalia(x.root);
    }

    public static float avalia(BNode<String> n)
    {
        if(isNum(n.getElement()))
        {
            return Float.valueOf(n.getElement());
        }
        else
        {
            return operacao(n.getElement(),avalia(n.getLeft()),avalia(n.getRight()));
        }
    }

    public static float operacao(String op, float a, float b)
    {
        if(op.equals("+"))
        {
            return a + b;
        }
        else if(op.equals("-"))
        {
            return a - b;
        }
        else if(op.equals("*"))
        {
            return a * b;
        }
        else
        {
            return a / b;
        }
    }


    public static void main(String[] args)
    {
        String postfix="2 8 - 9 / 7 * 67 7 5 * + 8 7 * / -";

        //String postfix="30 4 2 - 5 * 7 3 + / +";

        //String postfix = "3 10 -";

        ArrayStack<BTree<String>> stack = new ArrayStack<>(20);

        String token;
        StringTokenizer st = new StringTokenizer(postfix,"* + - /",true);

        BTree <String> node;
        BTree <String> node2;

        while (st.hasMoreTokens())
        {
            token = st.nextToken();
            System.out.println("lido:" + token);

            if(!(token.equals(" ")))
            {
                if(isNum(token))
                {
                    stack.push(new BTree<>(token));
                }
                else
                {
                    node2 = stack.pop();
                    node = stack.pop();
                    stack.push(new BTree<>(token, node.root, node2.root));
                }
            }
        }

        BTree<String> t = stack.top();

        System.out.println("Em ordem");
        t.emOrdem();
        System.out.println("\nPos ordem");
        t.postOrdem();
        System.out.println("\nPre ordem");
        t.preOrdem();

        System.out.println("\n" + avalia(t));
    }

}