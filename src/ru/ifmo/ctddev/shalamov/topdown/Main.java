package ru.ifmo.ctddev.shalamov.topdown;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class Main {


    public static void main(String[] args) {
        Parser parser = new Parser();

        if (args.length == 0) {
            System.out.println("no args");
            System.exit(1);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String s;
            int i = 0;
            while ((s = br.readLine()) != null) {
                ++i;
                try {
                    Tree t = parser.parse(s);
                    System.out.println("test: " + i);
                    System.out.println(t.toString());
                    System.out.println();
                    t.writeGraph("./tmp/graph" + i + ".dot");
                } catch (ParseException e) {
                    System.out.println("test: " + i);
                    e.printStackTrace();
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //System.out.println("success!");
    }
}
