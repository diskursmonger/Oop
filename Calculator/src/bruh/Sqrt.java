package bruh;

import java.util.logging.Level;

public class Sqrt implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        Double a, res;
        try {
            a = Double.parseDouble(context.popStack());
            res = Math.sqrt(a);
            context.pushStack(res.toString());
        } catch (NumberFormatException | NullPointerException error) {
            a = context.defines.get(context.popStack());
            if (a == null) {
                Calculator.logger.log(Level.WARNING, "Try to sqrt nothing");
            } else {
                res = Math.sqrt(a);
                context.pushStack(res.toString());
            }
        }
    }
}
