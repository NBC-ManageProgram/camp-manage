package campManage.service;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
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

        studentList.add(new Student(1, name, requireSubjects, scores, state));
    }

    public void deleteStudent(Student student) {
        studentList.delete(student);
    }

    public Student getStudentByStudentId(int studentId) {
        return StudentList.getInstance().getStudentByStudentId(studentId);
    }
}
