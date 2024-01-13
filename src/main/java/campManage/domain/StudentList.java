package campManage.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

    private List<Student> students = new ArrayList<>();

    private static final StudentList INSTANCE = new StudentList();
    private static final int NEXT_ID = 1;

    private StudentList() {
    }

    public static StudentList getInstance() {
        return INSTANCE;
    }

    public void add(Student student) {
        students.add(student);
    }

    public Student getLastStudents() {
        return students.get(students.size()-1);
    }

    public int getNextId() {
        return students.size()+NEXT_ID;
    }
}

