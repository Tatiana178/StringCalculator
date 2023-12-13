import org.example.StringCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StringCalculatorTest {

    @Test
    public void testEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void testOneNumber() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void testTwoNumbers() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void testNewLineDelimiter() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StringCalculator.add("-1,2"));
        assertEquals("Negatives not allowed: -1", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
                StringCalculator.add("2,-4,3,-5"));
        assertEquals("Negatives not allowed: -4,-5", exception.getMessage());
    }

    @Test
    public void testNumbersGreaterThan1000() {
        assertEquals(2, StringCalculator.add("1001,2"));
    }

    @Test
    public void testAnyLengthDelimiters() {
        assertEquals(6, StringCalculator.add("//[|||]\n1|||2|||3"));
    }

    @Test
    public void testMultipleDelimiters() {
        assertEquals(6, StringCalculator.add("//[|][%]\n1|2%3"));
    }

    @Test
    public void testMultipleDelimitersOfAnyLength() {
        assertEquals(6, StringCalculator.add("//[||][%%]\n1||2%%3"));
    }
}

