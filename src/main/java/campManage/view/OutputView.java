package campManage.view;

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

}
