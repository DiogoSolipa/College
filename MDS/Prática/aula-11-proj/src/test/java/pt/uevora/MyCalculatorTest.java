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
        assertEquals(5D, calculator.execute("2+3"));
    }

    @Test
    public void sumTwoNegatives() throws Exception
    {
        assertEquals(-5D, calculator.execute("-2+-3"));
    }

    @Test
    public void testException() throws Exception
    {
        assertThrows(IllegalArgumentException.class, () -> { calculator.execute("2*A"); } );
    }

    @Test
    public void subtractTwoNegatives() throws Exception//multiple operands not implemented so it throws an exception
    {
        assertThrows(IllegalArgumentException.class, () -> { calculator.execute(("-1--3"));});
    }

    @Test
    public void multTwoPositives() throws Exception
    {
        assertEquals(6D, calculator.execute("2*3"));
    }

    @Test
    public void multWithNegatives() throws Exception //multiple operands not implemented so it throws an exception
    {
        assertThrows(IllegalArgumentException.class, () -> { calculator.execute(("2*-3"));});
    }
    @Test
    public void divTwoPositives() throws Exception
    {
        assertEquals(0D, calculator.execute("2/3"));
    }
    @Test
    public void divWithNegatives() throws Exception
    {
        assertThrows(IllegalArgumentException.class, () -> { calculator.execute("2/-3");});
    }
    @Test
    public void divRemainTwoPositives() throws Exception
    {
        assertEquals(2D, calculator.execute("2%3"));
    }
    @Test
    public void divRemainWithNegatives() throws Exception
    {
        assertThrows(IllegalArgumentException.class, () -> { calculator.execute("2%3");});
    }

}