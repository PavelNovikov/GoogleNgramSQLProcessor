package main;

import googleNgramScan.FileWordEntity;
import googleNgramScan.UnigramFileIterator;
import logic.WordData;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Pavel Novikov on 16.07.14.
 */
public class main {

    public static void main(String[] arguments) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Map<String, WordData> wordMap = new HashMap<String, WordData>();
        UnigramFileIterator unigramIterator = new UnigramFileIterator("input/googlebooks-eng-all-1gram-20120701-g");
        double mileStone = 0.001;
        double step = 0.001;
        FileWordEntity fileWordEntity;
        while(unigramIterator.hasNext() && unigramIterator.getPartitionProcessed() < 0.003){

            fileWordEntity = unigramIterator.next();
            WordData wordData;
            if (wordMap.containsKey(fileWordEntity.getWord())){
                wordData = wordMap.get(fileWordEntity.getWord());
            } else {
                wordData = new WordData();
                wordData.setWord(fileWordEntity.getWord());
                wordMap.put(fileWordEntity.getWord(), wordData);
            }
            wordData.setYearFrequency1(fileWordEntity.getYear(), fileWordEntity.getFreq1());
            wordData.setYearFrequency2(fileWordEntity.getYear(), fileWordEntity.getFreq2());
            session.saveOrUpdate(wordData);



            if (unigramIterator.getPartitionProcessed() > mileStone ){

                System.out.println(mileStone +  " of file processed." );
                mileStone += step;
                session.flush();
            }
        }
        System.out.println("Конец!");

        session.getTransaction().commit();
        session.close();
    }





}

