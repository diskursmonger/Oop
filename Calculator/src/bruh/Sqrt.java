package bruh;

public class Sqrt implements Operation{
    @Override
    public void toDo(String[] command, Context context) {
        Double a;
        try{
            a = Double.parseDouble(context.popStack());
        } catch (NullPointerException error){
            a = context.getDefineValue(context.popStack());
        }
        Double res = Math.sqrt(a);
        context.pushStack(res.toString());
    }
}
