package bruh;

public class Print implements Operation{
    @Override
    public void toDo(String[] command, Context context) {
        try {
            System.out.println(context.stack.get(context.stack.size() - 1));
        } catch(Exception error) {
            System.err.println("nononono");
        }
    }
}
