package campManage.view;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.regex.Pattern;

public class InputView {

    private static final String INPUT_ERROR_MESSAGE = "[ERROR] 잘못된 값을 입력하였습니다. 다시 입력해주세요.";
    private static final String NAME_ERROR_MESSAGE = "[ERROR] 잘못된 입력입니다. 공백을 제외한 한글로 입력해 주세요.";
    private static final int INPUT_START_RANGE = 1;
    private static final int MANAGE_MENU_END_RANGE = 3;
    private static final int MANAGE_STUDENT_END_RANGE = 5;
    private static final int MANAGE_SCORE_END_RANGE = 4;
    private static final int STATE_END_RANGE = 3;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[가-힣]{2,10}$");


    public int manageMenu() {
        return getInputWithValidation(this::readUserInput, this::validateManageMenuRange);
    }

    public int manageStudent() {
        return getInputWithValidation(this::readUserInput, this::validateManageStudentRange);
    }

    public int manageScore() {
        return getInputWithValidation(this::readUserInput, this::validateManageScoreRange);
    }


    private <T> T getInputWithValidation(IntSupplier inputSupplier, Function<Integer, T> function) {
        while (true) {
            try {
                int input = inputSupplier.getAsInt();
                return function.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(INPUT_ERROR_MESSAGE);
            }
        }
    }

    public String name() {
        while (true) {
            try {
                String input = readUserNameInput();
                validateName(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(NAME_ERROR_MESSAGE);
            }
        }
    }

    public List<Subject> requireSubject() {
        while (true) {
            List<Subject> subjects = new ArrayList<>();
            try {
                String input = readUserNameInput();
                String[] numbers = input.split(" ");
                // vlidate(numbers);
                for (String number : numbers) {
                    subjects.add(Subject.getRequireSubjectByOrdinal(Integer.parseInt(number)));
                }
                return subjects;
            } catch (IllegalArgumentException e) {
                System.out.println(NAME_ERROR_MESSAGE);
            }
        }
    }

    public List<Subject> optionalSubject() {
        while (true) {
            List<Subject> subjects = new ArrayList<>();
            try {
                String input = readUserNameInput();
                String[] numbers = input.split(" ");
                // vlidate(numbers);
                for (String number : numbers) {
                    subjects.add(Subject.getOptionalSubjectByOrdinal(Integer.parseInt(number)));
                }
                return subjects;
            } catch (IllegalArgumentException e) {
                System.out.println(NAME_ERROR_MESSAGE);
            }
        }
    }

    public State state() {
        while (true) {
            try {
                int input = readUserInput();
                return State.getStateByInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println(NAME_ERROR_MESSAGE);
            }
        }
    }

    private int validateManageMenuRange(int manageMenuNumber) {
        if (INPUT_START_RANGE <= manageMenuNumber && manageMenuNumber <= MANAGE_MENU_END_RANGE) {
            return manageMenuNumber;
        }
        throw new IllegalArgumentException();
    }

    private int validateManageStudentRange(int manageStudentNumber) {
        if (INPUT_START_RANGE <= manageStudentNumber
            && manageStudentNumber <= MANAGE_STUDENT_END_RANGE) {
            return manageStudentNumber;
        }
        throw new IllegalArgumentException();
    }

    private int validateManageScoreRange(int manageScoreNumber) {
        if (INPUT_START_RANGE <= manageScoreNumber && manageScoreNumber <= MANAGE_SCORE_END_RANGE) {
            return manageScoreNumber;
        }
        throw new IllegalArgumentException();
    }

    private void validateName(String input) {
        if (!NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

//    private int validateStateRange(int stateNumber) {
//        if (INPUT_START_RANGE <= stateNumber && stateNumber <= STATE_END_RANGE) {
//            return stateNumber;
//        }
//        throw new IllegalArgumentException();
//    }

    private String readUserNameInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private int readUserInput() {
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    public int deleteStudentId() {
        while (true) {
            try {
                int studentId = readUserInput();
                StudentList studentList = StudentList.getInstance();
                return studentList.validateStudentsId(studentId);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다. 알맞은 고유번호를 입력해주세요.");
            }
        }
    }


    public int userDeleteChoice() {
        while (true) {
            try {
                int userDeleteChoice = readUserInput();
                if (userDeleteChoice == 1 || userDeleteChoice == 2) {
                    return userDeleteChoice;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다. 올바른 번호를 입력해주세요.");
            }
        }
    }

    // 학생 고유번호 검증 받고 가져오기
    public int readStudentId() {
        while (true) {
            try {
                int studentId = readUserInput();
                StudentList studentList = StudentList.getInstance();
                return studentList.validateStudentsId(studentId);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다. 유효한 고유번호를 입력해주세요.");
            }
        }
    }

    // 점수 입력할 과목 선택받기
    public int SelectedSubject(int size) {
        while (true) {
            try {
                int subjectID = readUserInput();
                if (subjectID > 0 && subjectID <= size) {
                    return subjectID - 1;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다. ");
            }
        }

    }


    public int inputPerScore() {
        int perscore = readUserInput();
        return perscore;
    }
}
