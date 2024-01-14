package campManage.view;

import campManage.domain.Score;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
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

    public void backToManageMenu() {
        System.out.println("이전 메뉴로 돌아갑니다.");
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

    //이도연
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

    public static void createScore(Student student, int subjectIndex) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            student.getStudentId() + "|" + student.getName() + "|" + student.getSubject()
                .get(subjectIndex).getName() + "|" );
        System.out.println("[[ 점수를 입력하세요 ]]");


    }


    public static void createScoreComplete(Student student, int subjectIndex, int subjectScore) {
        System.out.println(SEPARATE_LINE);
        System.out.println(
            student.getStudentId() + "|" + student.getName() + "|" + student.getSubject()
                .get(subjectIndex).getName() + "|"+student.getScores().get(0).getScorePerRoundSize()+"|" + subjectScore + "|등급");

    }
}
