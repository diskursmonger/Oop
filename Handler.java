package bruh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Handler {
    public void code(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> ABC = new HashMap<>();
        HashSet<Statistics> statistics = new HashSet<>();
        Scanner scannerABC = new Scanner(new File("Texts/ABC.txt"));
        FileWriter FileStatistics = new FileWriter("Texts/Statistics.txt");
        String symbol;
        while (scannerABC.hasNext()) {
            symbol = scannerABC.next();
            Statistics stat = new Statistics();
            stat.symb = symbol;
            statistics.add(stat);
            ABC.put(symbol, scannerABC.nextLine());
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            symbol = scanner.next();
            Iterator<Statistics> iter = statistics.iterator();
            while (iter.hasNext()) {
                Statistics st = iter.next();
                if (st.symb.contains(symbol)) {
                    st.num = st.num + 1;
                }
            }
            stringBuilder.append(ABC.get(symbol));
        }
        FileWriter writer = new FileWriter("Texts/Out.txt");
        int indexErr = stringBuilder.toString().indexOf("null");
        if (indexErr == -1) {
            writer.write(stringBuilder.toString());
            System.out.println(stringBuilder);
        } else {
            System.err.println("There's wrong symbol");
        }
        Iterator<Statistics> i = statistics.iterator();
        while (i.hasNext()) {
            Statistics st = i.next();
            if (st.num != 0) FileStatistics.write(st.makeStr(st.symb, st.num) + "\n");
        }
        FileStatistics.close();
        writer.close();
    }

    public void decode(File file) throws IOException {
        StringBuilder stringBuilderDec = new StringBuilder();
        Map<String, String> ABCdec = new HashMap<>();
        HashSet<Statistics> statistics = new HashSet<>();
        Scanner scannerABCdec = new Scanner(new File("Texts/ABC.txt"));
        String symbl, symblCode;
        while (scannerABCdec.hasNext()) {
            symbl = scannerABCdec.next();
            symblCode = scannerABCdec.next();
            ABCdec.put(symblCode,symbl);
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            stringBuilderDec.append(ABCdec.get(scanner.next()));
        }
        FileWriter writerDec = new FileWriter("Texts/Out.txt");
        int indexErr = stringBuilderDec.toString().indexOf("null");
        FileWriter statisticsDec = new FileWriter("Texts/Statistics.txt");
        if (indexErr == -1) {
            char[] chars = stringBuilderDec.toString().toCharArray();
            int i = 0;
            while (i != chars.length) {
                Statistics stat = new Statistics();
                stat.symb = chars[i] + "";
                Iterator<Statistics> it = statistics.iterator();
                int check = 0;
                while (it.hasNext()) {
                    Statistics currSt = it.next();
                    if (currSt.equalSymbol(stat.symb)) {
                        check++;
                    }
                }
                if (check == 0) {
                    statistics.add(stat);
                }
                i++;
            }
            i = 0;
            while (i != chars.length) {
                Iterator<Statistics> iter = statistics.iterator();
                while (iter.hasNext()) {
                    Statistics st = iter.next();
                    if (st.symb.contains("" + chars[i])) {
                        st.num = st.num + 1;
                    }
                }
                i++;
            }
            Iterator<Statistics> it = statistics.iterator();
            while (it.hasNext()) {
                Statistics st = it.next();
                if (st.num != 0 && !st.symb.equals(" ")) statisticsDec.write(st.makeStr(st.symb, st.num) + "\n");
            }
            writerDec.write(stringBuilderDec.toString());
            System.out.println(stringBuilderDec);
        } else {
            System.err.println("There's wrong symbol");
        }
        writerDec.close();
        statisticsDec.close();
    }
}
