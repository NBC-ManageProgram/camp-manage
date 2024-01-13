package campManage.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentList {


    private List<Student> students = new ArrayList<>();

    private static final StudentList INSTANCE = new StudentList();
    private static final int INDEX = 1;


    private StudentList() {
    }

    public static StudentList getInstance() {
        return INSTANCE;
    }

    public void add(Student student) {
        students.add(student);
    }

    public Student getLastStudents() {
        return students.get(students.size() - INDEX);
    }

    public int getNextId() {
        return students.size() + INDEX;
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

    public void delete(Student student) {
        students.remove(student);

    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentByStudentId(int studentId) {
        return students.stream()
            .filter(student -> student.getStudentId() == studentId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException());
    }

    public int validateStudentsId(int studentId) { // return int
        boolean validate = students.stream()
            .anyMatch(student -> student.getStudentId() == studentId);
        if (validate) {
            return studentId;
        }
        throw new IllegalArgumentException();
    }
}

