package bruh;

import java.util.logging.Level;

public class Push implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        Double define;
        try {
            double a = Double.parseDouble(command[1]);
            context.pushStack(command[1]);
        } catch (NumberFormatException error) {
            define = context.defines.get(command[1]);
            if (define == null) {
                Calculator.logger.log(Level.WARNING, "Try to push uninitialized variable");
            } else {
                context.pushStack(define.toString());
            }
        }
    }
}
