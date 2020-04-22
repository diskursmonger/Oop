package bruh;

public class Division implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        Double a = 0.0, b = 0.0;
        try {
            a = Double.parseDouble(context.popStack());
        } catch (NumberFormatException error) {
            a = context.getDefineValue(context.popStack());
        }
        try {
            b = Double.parseDouble(context.popStack());
        } catch (NumberFormatException error) {
            b = context.getDefineValue(context.popStack());
        }
        Double res = b / a;
        context.pushStack(res.toString());
    }
}
