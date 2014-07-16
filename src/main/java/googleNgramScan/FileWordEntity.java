package googleNgramScan;

/**
 * Created by Pavel Novikov on 16.07.14.
 */
public class FileWordEntity {
    private String word;
    private Integer year;
    private Long freq1;
    private Long freq2;

    public Long getFreq2() {
        return freq2;
    }

    public Long getFreq1() {

        return freq1;
    }

    public Integer getYear() {

        return year;
    }

    public String getWord() {

        return word;
    }

    public FileWordEntity(String word, Integer year, Long freq1, Long freq2) {
        this.word = word;
        this.year = year;
        this.freq1 = freq1;
        this.freq2 = freq2;
    }
}
