package campManage.view;


import campManage.domain.Score;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import campManage.domain.State;
import campManage.domain.SubjectGrade;
import java.util.List;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String SEPARATE_LINE = NEWLINE + "==================================";


    public void manageMenu() {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "내일배움캠프 수강생 관리 프로그램 실행 중...\n" +
                "1. 수강생 관리\n" +
                "2. 점수 관리\n" +
                "3. 프로그램 종료\n" +
                "관리 항목을 선택하세요...");
    }

    public void manageStudent() {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "1. 수강생 등록\n" +
                "2. 수강생 조회\n" +
                "3. 수강생 수정\n" +
                "4. 수강생 삭제\n" +
                "5. 돌아가기\n" +

                "관리 항목을 선택하세요...");
    }

    public void manageScore() {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "1. 점수 등록\n" +
                "2. 점수 조회\n" +
                "3. 점수 수정\n" +
                "4. 돌아가기\n" +

                "관리 항목을 선택하세요...");
    }

    public void createStudent() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 이름을 입력해주세요. ]]");
    }

    public void createRequireSubject() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 필수 과목을 3개 이상 선택해주세요. ]]\n" +
            "((ex) 1 2 3)\n" +
            "1. Java\n" +
            "2. 객체지향\n" +
            "3. Spring\n" +
            "4. JPA\n" +
            "5. MYSQL");
    }

    public void createOptionalSubject() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 선택 과목을 2개 이상 선택해주세요. ]]\n" +
            "((ex) 1 2)\n" +
            "1. 디자인 패턴\n" +
            "2. Spring Security\n" +
            "3. Redis\n" +
            "4. MongoDB");
    }

    public void createState() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 상태를 설정해주세요 ]]\n" +
            "1. Green\n" +
            "2. Yellow\n" +
            "3. Red");
    }

    public void updateId() {
        System.out.println(SEPARATE_LINE);
        System.out.println("\"[[ 수강생의 고유 번호를 입력해주세요 ]]\"");
    }

    public void updateList(Student student) {
        String format = "|  %d  |  %s  |  %s  |\n";
        System.out.println(SEPARATE_LINE);
        System.out.printf(format, student.getStudentId(), student.getName(), student.getState());
        System.out.println("\"1. 이름 정보 수정\"\n"
            + "\"2. 상태 정보 수정\"\n"
            + "\"3. 돌아가기\"");
    }

    public void updateName(Student student) {
        String format = "|  %d  |  %s  |  %s  |\n";
        System.out.println(SEPARATE_LINE);
        System.out.printf(format, student.getStudentId(), student.getName(), student.getState());
        System.out.println("\"[[ 수정할 이름을 입력해주세요 ]]\"");
    }

    public void updateState(Student student) {
        String format = "|  %d  |  %s  |  %s  |\n";
        System.out.println(SEPARATE_LINE);
        System.out.printf(format, student.getStudentId(), student.getName(), student.getState());
        System.out.println("\"1. Green\"\n"
            + "\"2. Yellow\"\n"
            + "\"3. Red\"");
    }

    public void updateComplete(Student student) {
        String format = "|  %d  |  %s  |  %s  |\n";
        System.out.println(SEPARATE_LINE);
        System.out.println("\"[[수정이 완료되었습니다.]]\"");
        System.out.printf(format, student.getStudentId(), student.getName(), student.getState());
    }

    public void checkIsEmpty() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[ERROR] 수강생이 등록이 되어 있지 않습니다. 수강생 등록을 먼저 해주세요");
    }

    public void createComplete() {
        Student student = StudentList.getInstance().getLastStudents();

        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 등록이 완료되었습니다. ]]");
        System.out.printf("|  %d  |  %s  |  ", student.getStudentId(), student.getName());

        student.getSubject().forEach(subject -> {
            System.out.print(subject.getName());
            if (student.getSubject().indexOf(subject) < student.getSubject().size() - 1) {
                System.out.print(", ");
            }
        });

        System.out.printf("  |  %s  |" + NEWLINE, student.getState());
        System.out.printf("고유번호는 [ %d ] 번 입니다.", student.getStudentId());
    }

    public void backToManageMenu() {
        System.out.println("이전 메뉴로 돌아갑니다.");
    }

    public void getStudentStateMessage() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 수강생 상태별 수강생 정보 ]]");
    }

    public void getStudentStateLi(List<Student> students, State state) {
        System.out.println("[ " + state + " ]");
        for (Student student : students) {
            String format = "|  %d  |  %s  |\n";
            System.out.printf(format, student.getStudentId(), student.getName());
        }
        System.out.println();
    }

    public void deleteStudentId() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 삭제할 고유번호를 입력하세요 ]]");
    }

    public void isRealDelete(Student student) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "\"[[ 삭제하시겠습니까? ]]\"\n"
                + "\"|  " + student.getStudentId() + "  |  " + student.getName() + "  |  "
                + student.getState() + "  |    " + student.getSubject() + "    |\"\n"
                + "\"1. 삭제 2. 돌아가기\""
        );
    }

    public void deleteCorrect() {
        System.out.println("삭제가 완료되었습니다");
    }

    public void inputStudentId() {
        System.out.println(SEPARATE_LINE);
        System.out.println("[[ 고유번호를 입력하세요 ]]");
    }

    public void ShowStudentName(Student student, List<Subject> subjects) {
        System.out.println(SEPARATE_LINE);
        System.out.println(student.getStudentId() + "|" + student.getName() + "|");
        System.out.println("[[ 과목을 입력하세요 ]]");

        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            System.out.println((i + 1) + ". " + subject.getName());
        }
    }

    public static void createScore(Student student, int subjectIndex, int round) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            student.getStudentId() + " | " + student.getName() + " | " + student.getSubject()
                .get(subjectIndex).getName() + " | " + round + "회차");
        System.out.println("[[ 점수를 입력하세요 ]]");


    }


    public static void createScoreComplete(Student student, int subjectIndex, int subjectScore,
        int round, SubjectGrade grade) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            student.getStudentId() + " | " + student.getName() + " | " + student.getSubject()
                .get(subjectIndex).getName() + "| " + round + "회차 | " + subjectScore + " | " +
                grade + "등급");
        System.out.println("[[ 등록이 완료되었습니다. ]]");

    }

    public void readStudent() {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "\"1. 수강생 전체 정보 조회\"\n"
                + "\"2. 수강생 상태별 수강생 목록 조회\"\n"
                + "\"3. 돌아가기\""
        );
    }

    public void getAllStudents(StudentList studentList) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "\"[[ 수강생 전체 정보  ]]\"\n"
                + String.format("| %-4s | %-4s | %-7s | %-3s |",
                "고유번호", "이름", "상태", "과목명")
        );
        for (Student student : studentList.getStudents()) {
            displayStudent(student);
        }
    }

    private void displayStudent(Student student) {
        System.out.format(
            "| %-6s | %-4s | %-8s | ",
            student.getStudentId(), student.getName(), student.getState()
        );
        System.out.println(student.getSubjectNames() + " |");
    }
    public void subjectSelect(Student student) {
        System.out.println(SEPARATE_LINE);
        ;
        System.out.println("| " + student.getStudentId() + " |" + student.getName() + " |");
        System.out.println("[[ 과목을 입력하세요. ]]");
        for (int i = 0; i < student.getSubject().size(); i++) {
            System.out.println((i + 1) + ". " + student.getSubject().get(i).getName() + "|" + student.getSubject().get(i).ordinal());
        }
    }
    public void roundSelect(Student student, int subject) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "| " + student.getStudentId() + " |" + student.getName() + " | " + student.getSubject().get(subject).getName() + " |");

        try {
            int round = student.getScores().get(subject).getScorePerRound().size();
            System.out.printf("총 회차 : %d\n", round);
            System.out.println("[[ 회차를 입력하세요 ]]");
        }catch (IndexOutOfBoundsException e){
            System.out.println("[ERROR] 회차를 수정할 수 없습니다. 다시 시험봐주세요");
        }
    }
    public void updateScore(Student student, int subject, int subjectRound){
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "| " + student.getStudentId() + " |" + student.getName() + " | " + student.getSubject()
                .get(subject).getName() + " | " + subjectRound + " |");
        System.out.println("현재 점수 : " + student.getScores().get(subject).getScorePerRound().get(subjectRound - 1));
        System.out.println("[[ 새로운 점수를 입력하세요 ]]");

    }
    public void successScore(Student student, int subject, int subjectRound, int subjectScore){
        System.out.println(SEPARATE_LINE);
        System.out.println(
            "| " + student.getStudentId() + " |" + student.getName() + " | " + student.getSubject()
                .get(subject).getName() + " | " + subjectRound + " | " + subjectScore + " |");
        System.out.println("[[ 수정이 완료 되었습니다 ]]");

    }

}
