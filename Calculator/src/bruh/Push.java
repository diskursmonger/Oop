package bruh;

public class Push implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        Double define;
        try {
            double a = Double.parseDouble(command[1]);
            context.pushStack(command[1]);
        } catch (NumberFormatException error) {
            define = context.getDefineValue(command[1]);
            context.pushStack(define.toString());
        }
        //context.pushStack(command[1]);
    }
}
