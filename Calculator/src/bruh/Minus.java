package bruh;

public class Minus implements Operation{
    @Override
    public void toDo(String[] command, Context context) {
        Double a, b;
        try {
            a = Double.parseDouble(context.popStack());
        } catch(NumberFormatException error){
            a = context.getDefineValue(context.popStack());
        }
        try {
            b = Double.parseDouble(context.popStack());
        } catch(NumberFormatException error) {
            b = context.getDefineValue(context.popStack());
        }
        Double res = b - a;
        context.pushStack(res.toString());
    }
}
