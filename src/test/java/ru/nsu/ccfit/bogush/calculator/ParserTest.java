package ru.nsu.ccfit.bogush.calculator;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {
    private int solve(String expression)
            throws IOException {
        return new Parser(new Lexer(new StringReader(expression))).calculate();
    }

    private void testCase(int expectedResult, String expression)
            throws IOException {
        assertEquals(expectedResult, solve(expression));
    }

    @Test
    public void plus()
            throws Exception {
        testCase(3, "2+1");
        testCase(2, "2+0");
        testCase(1, "0+1");
        testCase(10*11/2, "0+1+2+3+4+5+6+7+8+9+10");
    }

    @Test
    public void minus()
            throws Exception {
        testCase(0, "0-0");
        testCase(7, "10-3");
        testCase(-5, "10-15");
        testCase(-10*11/2, "0-1-2-3-4-5-6-7-8-9-10");
    }

    @Test
    public void brackets()
            throws Exception {
        testCase(4, "(2+2)");
        testCase(0, "4-(2+2)");
    }

    @Test(expected = Exception.class)
    public void extraClosingBracket()
            throws Exception {
        solve("2+2)");
    }

    @Test(expected = Exception.class)
    public void extraOpeningBracket()
            throws Exception {
        solve("(2+2");
    }

    @Test
    public void mult()
            throws Exception {
        testCase(6, "3 * 2");
        testCase(0, "123 * 0");
        testCase(0, "0 * 123");
    }

    @Test
    public void div()
            throws Exception {
        testCase(3, "6 / 2");
        testCase(0, "0 / 5");
    }

    @Test
    public void pow()
            throws Exception {
        testCase(9, "3^2");
        testCase(0, "3^-1");
        testCase(9, "(-3)^2");
        testCase(-27, "(-3)^3");
    }

    @Test
    public void unaryMinus()
            throws Exception {
        testCase(-1, " -1 ");
        testCase(1, "-1 + 2");
    }

    @Test
    public void complexExpressions()
            throws Exception {
        testCase(6, "2 + 2 * 2");
        testCase(8, "(2 + 2) * 2");
        testCase(16, "(2 + 2) * 2 ^ 2");
        testCase(512, "2 ^ 3 ^ 2");
        testCase(4, "1+(2-(3*(4/(5^6)/(-7))*8)-9)+10");
    }
}