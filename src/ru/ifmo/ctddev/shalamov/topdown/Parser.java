package ru.ifmo.ctddev.shalamov.topdown;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by viacheslav on 15.04.2015.
 */
public class Parser {
    /**
     * analyser, which provides tokens.
     */
    private LexicalAnalyzer lex;

    /**
     * tokens to be compared with.
     */
    private final Token tokenPLUS = new Unit(Character.toString(Token.PLUS));
    private final Token tokenMINUS = new Unit(Character.toString(Token.MINUS));
    private final Token tokenLPAREN = new Unit(Character.toString(Token.LPAREN));
    private final Token tokenRPAREN = new Unit(Character.toString(Token.RPAREN));
    private final Token tokenMUL = new Unit(Character.toString(Token.MUL));
    private final Token tokenEND = new Unit(Character.toString(Token.END));
    private final Token tokenNUMBER = new Number(1);

    Tree parse(InputStream is) throws ParseException {
        lex = new LexicalAnalyzer(is);
        lex.nextToken();
        return E();
    }

    /**
     * Provides a parse-tree of given string.
     *
     * @param string to be parsed
     * @return parse-tree
     * @throws ParseException
     */
    public Tree parse(String string) throws ParseException {
        return this.parse(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
    }


    private Tree E() throws ParseException {
        validate(lex.curToken(), tokenMINUS, tokenLPAREN, tokenNUMBER); //first

        Tree t1 = T();
        Tree t2 = U();
        Tree tree = new Tree("E", t1, t2);
        return tree;
    }

    private Tree U() throws ParseException {
        Tree tree;
        List<Tree> subtrees = new ArrayList<>();

        if (lex.curToken().isPLUS()) {
            subtrees.add(new Tree(lex.curToken().toString()));
            lex.nextToken();
            subtrees.add(T());
            subtrees.add(U());
        } else if (lex.curToken().isMINUS()) {
            subtrees.add(new Tree(lex.curToken().toString()));
            lex.nextToken();
            subtrees.add(T());
            subtrees.add(U());
        } else {
            subtrees.add(new Tree(Character.toString(Token.EPS)));
        }
        tree = new Tree("U", subtrees);
        validate(lex.curToken(), tokenRPAREN, tokenEND); //follow
        return tree;
    }

    private Tree T() throws ParseException {
        validate(lex.curToken(), tokenLPAREN, tokenMINUS, tokenNUMBER);//first

        Tree t1 = F();
        Tree t2 = V();
        Tree tree = new Tree("T", t1, t2);
        return tree;
    }

    private Tree V() throws ParseException {
        Tree tree;
        List<Tree> subtrees = new ArrayList<>();

        if (lex.curToken().isMUL()) {
            subtrees.add(new Tree(lex.curToken().toString()));
            lex.nextToken();
            subtrees.add(F());
            subtrees.add(V());

        } else {
            subtrees.add(new Tree(Character.toString(Token.EPS)));
        }

        validate(lex.curToken(), tokenPLUS, tokenMINUS, tokenRPAREN, tokenEND); //follow
        tree = new Tree("V", subtrees);

        return tree;
    }

    private Tree F() throws ParseException {
        validate(lex.curToken(), tokenMINUS, tokenLPAREN, tokenNUMBER);

        Tree tree;
        List<Tree> subtrees = new ArrayList<>();

        if (lex.curToken().isMINUS()) {
            subtrees.add(new Tree(lex.curToken().toString()));
            lex.nextToken();
            subtrees.add(F());
        } else if (lex.curToken().isLPAREN()) {
            subtrees.add(new Tree(lex.curToken().toString()));
            lex.nextToken();
            subtrees.add(E());
            validate(lex.curToken(), tokenRPAREN);
            subtrees.add(new Tree(tokenRPAREN.toString()));
            lex.nextToken();
        } else {
            subtrees.add(new Tree(lex.curToken().toString()));
            lex.nextToken();
        }
        tree = new Tree("F", subtrees);
        return tree;
    }

    /**
     * Validates the token "cur" to be one of "tokens" set. Otherwise ParseException is thrown.
     * @param cur
     * @param tokens
     * @throws ParseException
     */
    private void validate(Token cur, Token... tokens) throws ParseException {
        boolean oneOfGivenTokens = false;
        for (Token unit : tokens) {
            if (unit.isSame(cur)) {
                oneOfGivenTokens = true;
                break;
            }
        }
        if (!oneOfGivenTokens) {
            StringBuilder sb = new StringBuilder("Unexpected symbol "+cur+ "; expected one of the {");
            Arrays.asList(tokens).forEach(t -> sb.append(t.toString()).append(", "));
            sb.append("}");
            throw new ParseException(sb.toString(), lex.curPos());

        }
    }

}