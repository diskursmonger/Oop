package bruh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Context {
    ArrayList<String> stack;
    Map<String, Double> defines;

    Context() {
        stack = new ArrayList<>();
        defines = new HashMap<>();
    }

    void pushStack(String value) {
        stack.add(value);
    }

    String popStack() {
        String line = "";
        try {
            line = stack.remove(stack.size() - 1);
        } catch (IndexOutOfBoundsException error) {
            Calculator.logger.log(Level.WARNING, "Stack is empty");
        }
        return line;
    }

    void addDefine(String designation, String value) {
        try {
            Double dbl = Double.parseDouble(designation);
            Calculator.logger.log(Level.WARNING, "You try to define Double to Double");
        } catch (NumberFormatException error) {
            defines.put(designation, Double.parseDouble(value));
        }
    }

    double getDefineValue(String designation) {
        double define = 0.0;
        try {
            define = defines.get(designation);
            return define;
        } catch (NullPointerException error) {
            Calculator.logger.log(Level.WARNING, "There isn't define of " + designation);
        }
        return define;
    }
}
