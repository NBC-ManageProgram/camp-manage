package campManage.domain;

import java.util.List;
import java.util.Map;

public class Score {
    private final int subjectId;
    private final int studentId;
    private Map<Integer, Integer> scorePerRound;
    private SubjectGrade subjectGrade;

    public Score(int subjectId, int studentId, Map<Integer, Integer> scorePerRound,
        SubjectGrade subjectGrade) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.scorePerRound = scorePerRound;
        this.subjectGrade = subjectGrade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public Map<Integer, Integer> getScorePerRound() {
        return scorePerRound;
    }

    public SubjectGrade getSubjectGrade() {
        return subjectGrade;
    }

}
