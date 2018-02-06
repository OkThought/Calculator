package ru.nsu.ccfit.bogush.calculator;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {
    private void testCase(int expectedResult, String expression)
            throws IOException {
        assertEquals(expectedResult, new Parser(new Lexer(new StringReader(expression))).calculate());
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
}