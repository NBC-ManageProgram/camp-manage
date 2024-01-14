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



    public void addScore(Score score) {
        scores.add(score);
    }

}
