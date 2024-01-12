package campManage.domain;

import java.util.List;

public class Student {
    private final int studentId;
    private String name;
    private List<Subject> subject;
    private List<Score> scores;
    private State state;

    public Student(int id, String name, List<Subject> subject, List<Score> scores, State state) {
        this.studentId = id;
        this.name = name;
        this.subject = subject;
        this.scores = scores;
        this.state = state;
    }

    public boolean checkId(int id){
        return this.studentId == id;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeState(State state){
        this.state = state;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public List<Score> getScores() {
        return scores;
    }

    public State getState() {
        return state;
    }
}
