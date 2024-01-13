package campManage.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void delete(Student student) {
        students.remove(student);

    }

    public void getStudents() {
        for (Student student : students) {
            System.out.format(
                "| %-6s | %-4s | %-8s | ",
                student.getStudentId(), student.getName(), student.getState()
            );

            String result = student.getSubject().stream().map(Subject::getName)
                .collect(Collectors.joining(", "));
            System.out.println(result + " |");
        }
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

