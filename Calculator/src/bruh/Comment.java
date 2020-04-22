package bruh;

public class Comment implements Operation {
    @Override
    public void toDo(String[] command, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= command.length - 1; i++) {
            stringBuilder.append(command[i]).append(" ");
        }
        System.out.println(stringBuilder);
    }
}
