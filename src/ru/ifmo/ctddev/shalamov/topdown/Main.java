package ru.ifmo.ctddev.shalamov.topdown;

import java.text.ParseException;

public class Main {



    public static void main(String[] args) {
        Parser parser = new Parser();

        if(args.length == 0)
        {
            System.out.println("no expression!");
            System.exit(1);
        }

        try {
            Tree t = parser.parse(args[0]);
            System.out.println(t.toString());
            t.writeGraph("./tmp/graph.dot");
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }




        System.out.println("success!");
    }
}
