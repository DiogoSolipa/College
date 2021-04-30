import org.junit.Test;
import java.lang.*;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void UserTestCardContainsNumbersShouldReturnTrue() throws Exception
    {
        User user = new User("Joao", "001","aluno");

        assertTrue("Card Validation:", user.isCard(user));
    }


    @Test
    public void UserTestRoleShouldBeAlunoOrDocente() throws Exception
    {
        User user = new User("Manuel", "002","aluno");

        assertTrue("Role Validation:", user.isRole(user));
    }


    @Test
    public void UserTestNameShouldOnlyContainLetters() throws Exception
    {
        User user = new User("Joaquim", "003","docente");

        assertTrue("Name Validation:", user.isName(user));
    }




}
