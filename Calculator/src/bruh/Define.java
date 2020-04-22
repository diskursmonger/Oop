package bruh;

public class Define implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        context.addDefine(command[1], command[2]);
    }
}
