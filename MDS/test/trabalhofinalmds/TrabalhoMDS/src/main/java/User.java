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


    public boolean isCard(User user) {
        char[] cartao = user.card.toCharArray();
        for (char c : cartao)
        {
            if(!Character.isDigit(c))
            {
                return false;
            }
        }
        return true;
    }


    public boolean isRole(User user)
    {
        String role1 = "aluno";
        String role2 = "docente";
        if(user.role.equals(role1) || user.role.equals(role2))
        {
            return true;
        }
        return false;
    }


    public boolean isName(User user)
    {
        char[] nome = user.name.toCharArray();

        for (char c : nome)
        {
            if (!Character.isLetter(c))
            {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString()
    {
        String s = "";
        s+=this.name+ " ; "+this.card+ " ; "+this.role+ " ; "+this.abs_num;
        return s;
    }
}
