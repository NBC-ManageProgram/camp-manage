package campManage.service;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import campManage.view.InputView;
import campManage.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class CampManageService {

    //    private final Student student;
//    private final Score score;
//    private final Subject subject;
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

    //public int getSelectedSubjectScore(Student student,List<Integer> perScore){
    //List<Score> scores = perScore;
    //return
    //}

}
