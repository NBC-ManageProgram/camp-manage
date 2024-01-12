package campManage.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

    List<Student> students = new ArrayList<>();
    private Student student;

    private static final StudentList INSTANCE = new StudentList();

    private StudentList() {
    }

    public static StudentList getInstance() {
        return INSTANCE;
    }

    public void add(Student student) {
        students.add(student);
    }

    public Student checkStudent(int id) {
        for (Student studentCheck : students) {
            if (studentCheck.checkId(id)) {
                return studentCheck;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean getStudentsIsEmpty() {
        return students.isEmpty();
    }

    public void getStudents() {
        for (Student student : students) {
            System.out.println(student.getStudentId());
            System.out.println(student.getName());
            System.out.println(student.getSubject());
            System.out.println(student.getScores());
            System.out.println(student.getState());
        }
    }
}

