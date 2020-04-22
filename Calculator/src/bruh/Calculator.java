package bruh;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Calculator {
    static Logger logger;

    static {
        try (FileInputStream ins = new FileInputStream("log1.txt")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(Calculator.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public static void calculator(String config, String in) {
        HashMap<String, Operation> loaded = new HashMap<>();
        Factory factory = new Factory(config);
        Context context = new Context();
        Scanner scanner;
        int stdin = 0;
        try {
            if (in.equals("no input file")) {
                scanner = new Scanner(System.in);
            } else {
                scanner = new Scanner(new File(in));
                stdin = 1;
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] ourCommand = line.split(" ");
                Operation operation;
                if (loaded.containsKey(ourCommand[0])) {
                    operation = loaded.get(ourCommand[0]);
                } else {
                    operation = factory.load(ourCommand[0]);
                    loaded.put(ourCommand[0], operation);
                }
                operation.toDo(ourCommand, context);
                if (stdin == 0) {
                    scanner = new Scanner(System.in);
                }
            }
        } catch (IOException error) {
            logger.log(Level.SEVERE, error.getMessage());
        }
    }

    public static void main(String[] args) {
        logger.log(Level.INFO, "Start app");
        if (args.length > 1) {
            calculator(args[0], args[1]);
        } else {
            calculator(args[0], "no input file");
        }
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(UnitTest.class);
    }
}