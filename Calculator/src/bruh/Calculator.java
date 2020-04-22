package bruh;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

public class Calculator {
    public static void calculator(String config, String in) throws IOException {
        HashMap<String, Operation> created = new HashMap<>();
        Factory factory = new Factory(config);
        Context context = new Context();
        Scanner scanner;
        int stdin = 0;
        try {
            if(in.equals("no input file")){
                scanner = new Scanner(System.in);
            } else {
                scanner = new Scanner(new File(in));
                stdin = 1;
            }
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] ourCommand = line.split(" ");
                Operation operation = null;
                if (created.containsKey(ourCommand[0])){
                    operation = created.get(ourCommand[0]);
                } else {
                    operation = factory.create(ourCommand[0]);
                    created.put(ourCommand[0],operation);
                }
                operation.toDo(ourCommand,context);
                if (stdin == 0) {
                    scanner = new Scanner(System.in);
                }
            }
        } catch (IOException error) {
            System.err.println(error);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            calculator(args[0], args[1]);
        } else {
            calculator(args[0], "no input file");
        }
    }
}