package campManage.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    List<Student> students = new ArrayList<>();

    public StudentList(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

}

