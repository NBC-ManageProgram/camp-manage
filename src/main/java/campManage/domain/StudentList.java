package campManage.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

    List<Student> students = new ArrayList<>();

    private static final StudentList INSTANCE = new StudentList();

    private StudentList() {
    }

    public static StudentList getInstance() {
        return INSTANCE;
    }

    public void add(Student student) {
        students.add(student);
    }

    public void delete(Student student){
        students.remove(student);
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

    public Student getStudentByStudentId(int studentId){
        return students.stream()
            .filter(student -> student.getStudentId() == studentId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException());
    }

    public int validateStudentsId(int studentId) { // return int
         boolean validate = students.stream()
            .anyMatch(student -> student.getStudentId() == studentId);
         if (validate){
             return studentId;
         }
         throw new IllegalArgumentException();
    }
}

