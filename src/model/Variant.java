package model;

/**
 * Created by BigBadVoodooDaddy on 28.10.2017.
 */
public class Variant {
    private String answer;
    private boolean correct;

    public Variant() {
    }

    public Variant(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public Variant (String answer) {
        boolean correct = answer.startsWith("+");
        this.answer = correct ? answer.substring(1) : answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String toString () {return answer;}
}
