package campManage.service;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import campManage.domain.SubjectCategory;
import campManage.domain.SubjectGrade;
import campManage.view.InputView;
import campManage.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class CampManageService {
    private static final String SCORE_ERROR_MESSAGE = "[ERROR] 아직 한번도 시험을 보지 않은 과목입니다.";

    private final StudentList studentList;

    public CampManageService() {
        this.studentList = StudentList.getInstance();
    }

    public void createStudent(String name, List<Subject> requireSubjects,
            List<Subject> optionalSubjects, State state) {
        requireSubjects.addAll(optionalSubjects);
        List<Score> scores = new ArrayList<>();
        studentList.add(new Student(studentList.getNextId(), name, requireSubjects, scores, state));
    }


    public void updateName(Student student, OutputView outputView, InputView inputView) {
        outputView.updateName(student);
        String name = inputView.checkSameName(student);
        student.changeName(name);
    }

    public void updateState(Student student, OutputView outputView, InputView inputView) {
        int STATE_INDEX = 1;
        outputView.updateState(student);
        int ordinal = inputView.selectStateNumber(student);
        for (State state : State.values()) {
            if (state.ordinal() + STATE_INDEX == ordinal) {
                student.changeState(state);
            }
        }
    }

    public void deleteStudent(Student student) {
        studentList.delete(student);
    }

    public Student getStudentByStudentId(int studentId) {
        return StudentList.getInstance().getStudentByStudentId(studentId);
    }

    public Score getSubjectScore(Student student, int selectedSubjectIndex) {
        // 선택한 과목 가져오기
        Subject selectedSubject = student.getSubject().get(selectedSubjectIndex);

        // 해당 과목에 대한 성적 가져오기
        List<Score> scores = student.getScores();
        for (Score score : scores) {
            if (score.getSubjectId() == selectedSubject.getSubjectId()) {
                return score; // 해당 과목에 대한 성적이 이미 존재하는 경우 반환
            }
        }
        // 해당 과목에 대한 성적이 없는 경우 새로운 성적 생성 후 반환
        Score newScore = new Score(selectedSubject.getSubjectId(), new ArrayList<>(),
                new ArrayList<>());
        student.addScore(newScore); // 생성한 성적을 학생에게 추가
        return newScore;
    }

    public SubjectGrade getSubjectGrade(Student student, int score, int subjectid) {
        SubjectGrade grade;
        if (student.getSubject().get(subjectid).getSubjectCategory() == SubjectCategory.REQUIRE) {
            grade = SubjectGrade.requireGetGrade(score);
        } else {
            grade = SubjectGrade.optionGetGrade(score);
        }
        return grade;
    }

    public void handleScoreCreation(Score subjectScore, int inputSubjectScore,
        SubjectGrade grade) {

        if (subjectScore.getScorePerRoundSize() >= 10) {
            throw new RuntimeException();
        } else {
            subjectScore.addScore(inputSubjectScore);
            subjectScore.addGrade(grade);
        }
    }



    public int getScoreIndex(Student student, int subjectId) {
        int index = 10;
        for (int i = 0; i < student.getScores().size(); i++) {
            if (student.getScores().get(i).getSubjectId() == subjectId) {
                index = i;
            }
        }
        return index;
    }

    public Score hasScore(Student student, int subjectId) {
        List<Score> scores = student.getScores();

        Subject selectedSubject = student.getSubject().get(subjectId);

        for (Score score : scores) {
            if(score.getSubjectId() == selectedSubject.getSubjectId()){
                return score;
            }
        }
        System.out.println(SCORE_ERROR_MESSAGE);
        throw new IllegalArgumentException();
    }
}
