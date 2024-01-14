package campManage.domain;

import java.util.List;
import java.util.Map;

public class Score {

    private final int subjectId;
    private List<Integer> scorePerRound;


    public Score(int subjectId, List<Integer> scorePerRound) {
        this.subjectId = subjectId;
        this.scorePerRound = scorePerRound;
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
    public int getScorePerRoundSize(){
        return this.scorePerRound.size();
    }



    public int isemptyScore(int subjectId) {
        for (int i = 1; i <= 10; i++) {
            boolean roundExists = scorePerRound.size() >= i;

            if (!roundExists) {

                System.out.println("과목 " + subjectId + " 회차: " + i);
                return i;

            }
        }

        return 0;
    }


}
