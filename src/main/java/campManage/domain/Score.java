package campManage.domain;

import java.util.List;

public class Score {

    private final int subjectId;
    private List<Integer> scorePerRound;
    private List<SubjectGrade> gradePerRound;


    public Score(int subjectId, List<Integer> scorePerRound, List<SubjectGrade> gradePerRound) {
        this.subjectId = subjectId;
        this.scorePerRound = scorePerRound;
        this.gradePerRound = gradePerRound;
    }

    public int getAverageScore() {
        return scorePerRound.stream().mapToInt(Integer::intValue).sum() / scorePerRound.size();
    }

    public int getSubjectId() {
        return subjectId;
    }

    public List<Integer> getScorePerRound() {
        return scorePerRound;
    }

    public void addScore(int score) {
        scorePerRound.add(score);
    }

    public void addGrade(SubjectGrade grade) {
        gradePerRound.add(grade);
    }

    public List<SubjectGrade> getGrade() {
        return gradePerRound;
    }

    public int getScorePerRoundSize() {
        return this.scorePerRound.size();
    }

    public int isemptyScore() {
        for (int i = 1; i <= 10; i++) {
            boolean roundExists = scorePerRound.size() >= i;
            if (!roundExists) {
                return i;
            }
        }
        return 11;
    }

    public void setScorePerRound(int round, int score) {
        scorePerRound.set(round - 1, score);
    }
}
