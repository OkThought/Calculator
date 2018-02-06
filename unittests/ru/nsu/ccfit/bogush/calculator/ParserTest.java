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
    }

    @Test
    public void minus()
            throws Exception {
        testCase(0, "0-0");
        testCase(7, "10-3");
        testCase(-5, "10-15");
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
}