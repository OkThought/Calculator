package ru.nsu.ccfit.bogush.calculator;

import java.io.IOException;
import java.util.Objects;

public class Parser {
    private Lexer lexer;
    private Lexeme currentLexeme;

    public Parser(Lexer lexer)
            throws IOException {
        this.lexer = lexer;
        this.currentLexeme = lexer.getLexeme();
    }

    public int calculate()
            throws IOException {
        int result = parseExpression();
        if (!Objects.equals(currentLexeme, Lexeme.EOF)) {
            throw new IllegalStateException("Reached end of expression before EOF lexeme");
        }

        return result;
    }

    /**
     * expr = term ± term ± ...
     */
    private int parseExpression()
            throws IOException {
        int result = parseTerm();
        LexemeType type;
        type = currentLexeme.getType();
        while (type == LexemeType.PLUS || type == LexemeType.MINUS) {
            int sign = type == LexemeType.PLUS ? 1 : -1;
            currentLexeme = lexer.getLexeme();
            result += sign * parseTerm();
            type = currentLexeme.getType();
        }
        return result;
    }

    /**
     * term = factor * / factor * / ...
     */
    private int parseTerm()
            throws IOException {
        int result = parseFactor();
        LexemeType type;
        type = currentLexeme.getType();
        while (type == LexemeType.MULT || type == LexemeType.DIV) {
            currentLexeme = lexer.getLexeme();
            if (type == LexemeType.MULT) {
                result *= parseFactor();
            } else {
                result /= parseFactor();
            }
            type = currentLexeme.getType();
        }
        return result;
    }

    /**
     * factor = atom ^ atom
     */
    private int parseFactor()
            throws IOException {
        int result = parseAtom();
        LexemeType type;
        type = currentLexeme.getType();
        while (type == LexemeType.POWER) {
            currentLexeme = lexer.getLexeme();
            result = (int) Math.pow(result, parseAtom());
            type = currentLexeme.getType();
        }
        return result;
    }

    /**
     * atom = number | (expr)
     */
    private int parseAtom()
            throws IOException {
        LexemeType type = currentLexeme.getType();
        int result;
        if (type == LexemeType.NUMBER) {
            result = Integer.parseInt(currentLexeme.getValue());
        } else if (type == LexemeType.OPEN_BRACKET) {
            currentLexeme = lexer.getLexeme();
            result = parseExpression();

            if (currentLexeme.getType() != LexemeType.CLOSE_BRACKET) {
                throw new IllegalStateException("Expected close bracket lexeme, found: " + currentLexeme);
            }
        } else {
            throw new IllegalStateException("Failed to parse atom on lexeme: " + currentLexeme);
        }

        currentLexeme = lexer.getLexeme();
        return result;
    }
}
