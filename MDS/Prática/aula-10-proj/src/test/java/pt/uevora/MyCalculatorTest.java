package pt.uevora;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCalculatorTest {

    MyCalculator calculator;

    @Before
    public void setUp(){
        calculator = new MyCalculator();
    }

    @After
    public void down(){
        calculator = null;
    }

    @Test
    public void testSum() throws Exception
    {
        Double result = calculator.execute("2+3");

        assertEquals(5D, (Object)result);
    }

    @Test
    public void test2Negatives() throws Exception
    {
        Double result = calculator.execute("-2+-3");

        assertEquals(-5D, (Object)result);
    }

    @Test
    public void testException() throws Exception
    {
        assertThrows(IllegalArgumentException.class, () -> { calculator.execute("2-3"); } );
    }
}

//perguntar se se pode generalizar um teste, tipo para qualquer expressao sem um +x