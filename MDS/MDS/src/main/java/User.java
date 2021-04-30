public class User
{
    String name;
    String card;
    String role;
    int abs_num;

    User(String nome, String cartao, String papel)
    {
        this.name = nome;
        this.card = cartao;
        this.role = papel;
        this.abs_num = 0;
    }

    String getName()
    {
        return this.name;
    }

    String getCard()
    {
        return this.card;
    }

    String getRole()
    {
        return this.role;
    }

    int getAbs_num()
    {
        return this.abs_num;
    }

    @Override
    public String toString()
    {
        String s = "";
        s+=this.name+ " ; "+this.card+ " ; "+this.role+ " ; "+this.abs_num;
        return s;
    }
}
