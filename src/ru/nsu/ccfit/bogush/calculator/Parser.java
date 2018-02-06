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
     * expr = atom ± atom ± ...
     */
    private int parseExpression()
            throws IOException {
        int result = parseAtom();
        LexemeType type;
        type = currentLexeme.getType();
        while (type == LexemeType.PLUS || type == LexemeType.MINUS) {
            int sign = type == LexemeType.PLUS ? 1 : -1;
            currentLexeme = lexer.getLexeme();
            result += sign * parseAtom();
            type = currentLexeme.getType();
        }
        currentLexeme = lexer.getLexeme();
        return result;
    }

    /**
     * atom = number
     */
    private int parseAtom()
            throws IOException {
        if (currentLexeme == null || currentLexeme.getType() != LexemeType.NUMBER) {
            throw new IllegalStateException("Failed to parse atom: " + currentLexeme + " is not a number");
        }
        int result = Integer.parseInt(currentLexeme.getValue());
        currentLexeme = lexer.getLexeme();
        return result;
    }
}
