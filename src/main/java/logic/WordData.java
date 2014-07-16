package logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavel Novikov on 16.07.14.
 */
@Entity
@Table(name = "words")
public class WordData {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long idKey;

    @Column(unique = true)
    private String word;

    @ElementCollection
    private Map<Integer, Long> frequencyMap1 = new HashMap<Integer, Long>();

    @ElementCollection
    private Map<Integer, Long> frequencyMap2 = new HashMap<Integer, Long>();

    public WordData() {

    }

    public void setWord(String word){
        this.word = word;
    }

    public void setYearFrequency1(int year, long frequency){
        frequencyMap1.put(year, frequency);
    }

    public void setYearFrequency2(int year, long frequency){
        frequencyMap2.put(year, frequency);
    }

}
