package ru.ifmo.ctddev.shalamov.topdown;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by viacheslav on 15.04.2015.
 */
public class LexicalAnalyzer {
    private InputStream is;
    private int curChar;
    private int curPos;
    private Token curToken;

    public LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        curPos = 0;
        curChar = -1;
        curToken = null;
        nextChar();
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), curPos);
        }
    }

    /**
     * skips all whitespace characters in input stream.
     *
     * @throws ParseException
     */
    private void skipWhitespaces() throws ParseException {
        while (Character.isWhitespace((char) curChar)) {
            nextChar();
        }
    }

    /**
     * "Makes a step" through input stream and returns new token.
     *
     * @return
     * @throws ParseException
     */
    public Token nextToken() throws ParseException {
        skipWhitespaces();
        if (curChar == -1) {
            curToken = new Unit(Character.toString(Token.END));
            return curToken;
        }

        Token token = Unit.fromChar((char) curChar);
        if (token != null) {
            nextChar();
            curToken = token;
            return token;
        } else if (Character.isDigit((char) curChar)) {
            StringBuilder sb = new StringBuilder();
            sb.append((char) curChar);
            while (true) {
                nextChar();
                if (Character.isDigit((char) curChar)) {
                    sb.append((char) curChar);
                } else break;
            }
            curToken = new Number(Integer.parseInt(sb.toString()));
            return curToken;
        } else {
            throw new ParseException("Illegal character: " + (char) curChar, curPos);
        }
    }

    /**
     * Returns current token (that was previously returned by nextToken())
     *
     * @return
     */
    public Token curToken() {
        return curToken;
    }

    public int curPos() {
        return curPos;
    }

}
