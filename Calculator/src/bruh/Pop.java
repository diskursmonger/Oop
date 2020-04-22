package bruh;

public class Pop implements Operation{
    @Override
    public void toDo(String[] command, Context context) {
        try{
            context.popStack();
        } catch(Exception error) {
            //logger (в самом попстеке есть кэтч на "стэк из эмпти")
        }
    }
}
