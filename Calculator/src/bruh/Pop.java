package bruh;

public class Pop implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        context.popStack();
    }
}
