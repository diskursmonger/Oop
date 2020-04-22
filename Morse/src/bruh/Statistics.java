package bruh;

public class Statistics{
    public String symb;
    int num = 0;
    public static String makeStr(String symbol,int num){
        return symbol + " occurs " + num + " times";
    }
    public boolean equalSymbol(String symbol){
        return this.symb.equals(symbol);
    }
}