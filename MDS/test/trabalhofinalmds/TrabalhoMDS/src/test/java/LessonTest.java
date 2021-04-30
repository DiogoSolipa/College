import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LessonTest {

    @Test
    public void LessonDataShouldHaveThatDateFormat() throws Exception
    {
        Lesson aula = new Lesson("2010-09-23","10:00");

        assertTrue("Date Validation:", aula.isDate(aula));
    }


    @Test
    public void LessonHoraShouldHaveThatHourFormat() throws Exception
    {
        Lesson aula = new Lesson("2010-10-29","10:00");

        assertTrue("Hour Validation:", aula.isHour(aula));
    }


}
