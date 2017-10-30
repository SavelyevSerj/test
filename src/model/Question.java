package model;

import java.util.List;

/**
 * Created by BigBadVoodooDaddy on 28.10.2017.
 */
public class Question {

    private String question;
    private String fileName;
    private String hint;
    private Variant [] variants;

    public Question(String question, String fileName, String hint, String variant1, String variant2, String variant3, String variant4) {
        this.question = question;
        this.fileName = fileName;
        this.hint = hint;
        this.variants[0] = new Variant(variant1);
        this.variants[1] = new Variant(variant2);
        this.variants[2] = new Variant(variant3);
        this.variants[3] = new Variant(variant4);
    }

    public Question() {
        this("", "", "", "", "", "", "");
    }

    public Question (List<String> stringList) {
        this(stringList.get(0), stringList.get(1), stringList.get(2), stringList.get(3),stringList.get(4), stringList.get(5), stringList.get(6));
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Variant[] getVariants() {
        return variants;
    }

    public void setVariants(Variant[] variants) {
        this.variants = variants;
    }
}
