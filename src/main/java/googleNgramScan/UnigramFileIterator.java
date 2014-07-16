package googleNgramScan;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Pavel Novikov on 16.07.14.
 */
public class UnigramFileIterator implements Iterator<FileWordEntity> {
    private Scanner scanner;
    private long totalLines;
    private long curLine;


    public UnigramFileIterator(String filename){
        try {
            File file = new File(filename);
            scanner = new Scanner(new FileReader(file));
            totalLines = countTotalLines(file);
            curLine = 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public double getPartitionProcessed(){
        return ((double) curLine) / totalLines;
    }


    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public FileWordEntity next() {
        String line = scanner.nextLine();
        String[] parts = line.split("\\s");
        String word = parts[0];
        Integer year = Integer.parseInt(parts[1]);
        Long freq1 = Long.parseLong(parts[2]);
        Long freq2 = Long.parseLong(parts[3]);
        curLine ++;
        return new FileWordEntity(word, year, freq1, freq2);
    }

    @Override
    public void remove() {
        throw new NotImplementedException();
    }

    private static long countTotalLines(File file) throws IOException {
        LineNumberReader lnr = null;
        lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        lnr.close();
        return lnr.getLineNumber();
    }

}

