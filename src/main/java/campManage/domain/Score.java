package campManage.domain;

import java.util.Map;

public class Score {

    private final int subjectId;
    private Map<Integer, Integer> scorePerRound;
    private SubjectGrade subjectGrade;

    public Score(int subjectId, Map<Integer, Integer> scorePerRound,
        SubjectGrade subjectGrade) {
        this.subjectId = subjectId;
        this.scorePerRound = scorePerRound;
        this.subjectGrade = subjectGrade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public Map<Integer, Integer> getScorePerRound() {
        return scorePerRound;
    }

    public SubjectGrade getSubjectGrade() {
        return subjectGrade;
    }

}
