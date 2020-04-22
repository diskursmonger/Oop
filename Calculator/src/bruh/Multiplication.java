package bruh;

public class Multiplication implements Operation{
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
        Double res = a * b;
        context.pushStack(res.toString());
    }
}
