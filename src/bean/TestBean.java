package bean;

import model.Question;
import model.Variant;
import model.model.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BigBadVoodooDaddy on 28.10.2017.
 */
@ManagedBean
@SessionScoped
public class TestBean implements Serializable{


    private User user = new User();
    private Question currentQuestion;
    private int numberOfCurrentQuestion;
    private List<Question> questions;
    private List<Question> subQuestionList;
    private List<Integer> rightAnswers;
    private List<Integer> userAnswers;
    private List<String> marks;
    private String mark;
    private int markedValue;
    private int number = 0;
    private String path = "http://www.berkut.mk.ua/download/test/";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getCurrentQuestion() {
        return subQuestionList.get(numberOfCurrentQuestion -1);
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getNumberOfCurrentQuestion() {
        return numberOfCurrentQuestion;
    }

    public void setNumberOfCurrentQuestion(int numberOfCurrentQuestion) {
        this.numberOfCurrentQuestion = numberOfCurrentQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getSubQuestionList() {
        return subQuestionList;
    }

    public void setSubQuestionList(List<Question> subQuestionList) {
        this.subQuestionList = subQuestionList;
    }

    public List<Integer> getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(List<Integer> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public List<Integer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public int getMarkedValue() {
        return markedValue;
    }

    public void setMarkedValue(int markedValue) {
        this.markedValue = markedValue;
    }

    public List<String> getMarks() {
        return marks;
    }

    public void setMarks(List<String> marks) {
        this.marks = marks;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPicturePath() {return path;}

    public String getThePicture() {return getPicturePath() + "img/" + getCurrentQuestion().getFileName();}

    @PostConstruct
    void init() {

        rightAnswers = new ArrayList<>();


        //формирование начального списка ответов пользователя
        userAnswers = new ArrayList<>();//инициализировали список ответов пользователя
        for (int i = 0; i < 20; i++) {
            userAnswers.add(0);
        }

        //формирование общего списка вопросов
        questions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(path + "qcpp.txt").openStream()))){
            List<String> strings = bufferedReader.lines().collect(Collectors.toList());
            int questionNumber = strings.size() / 8;
            for (int i = 0; i < questionNumber; i++) {
                Question question = new Question(strings.subList(i * 8, (i + 1) * 8));
                questions.add(question);
                System.out.println(i + " " + question.getQuestion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String startTheTest() {
        Collections.shuffle(questions);
        subQuestionList = questions.subList(1, 21);
        currentQuestion = subQuestionList.get(0);
        numberOfCurrentQuestion = 1;
        return "quiz";
    }

    public void answerTheQuestion() {
        numberOfCurrentQuestion++;
        userAnswers.set(number, markedValue);
        number++;
        markedValue = userAnswers.get(number);
        if (number == numberOfCurrentQuestion) number = 0;
    }

    public void skipTheQuestion() {
        numberOfCurrentQuestion++;
        if (numberOfCurrentQuestion == 20) numberOfCurrentQuestion = 1;
    }

    public void returnToPreviousQuestion () {
        numberOfCurrentQuestion--;
        if (numberOfCurrentQuestion == 1) numberOfCurrentQuestion = 20;
    }

    public String finishTheTest() {
        for (int i = 0; i < marks.size(); i++) {
            if (rightAnswers.get(0).equals(userAnswers.get(0))) {
                mark = "+";
                marks.add(mark);
            }
        }
        return "results";
    }
}
