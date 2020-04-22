package bruh;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Context {
    ArrayList<String> stack;
    Map<String,Double> defines;
    Context(){
        stack = new ArrayList<>();
        defines = new HashMap<>();
    }
    void pushStack(String value){
        stack.add(value);
    }
    String popStack(){
        if (stack.size() < 1) {
            System.out.println("stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }
    void addDefine(String designation, String value){
        defines.put(designation,Double.parseDouble(value));
    }
    double getDefineValue(String designation){
        double define = 0.0;
        try {
            define = defines.get(designation);
        } catch (NullPointerException e){
            System.out.println("no define");
        }
        return define;
    }
}
