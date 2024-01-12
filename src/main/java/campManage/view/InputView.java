package campManage.view;

import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import campManage.service.CampManageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.regex.Pattern;

public class InputView {

    private static final String INPUT_ERROR_MESSAGE = "[ERROR] 잘못된 값을 입력하였습니다. 다시 입력해주세요.";
    private static final String NAME_ERROR_MESSAGE = "[ERROR] 잘못된 입력입니다. 공백을 제외한 한글로 입력해 주세요.";
    private static final String UPDATE_INPUT_MESSAGE = "[ERROR] 잘못된 입력입니다. 유효한 고유번호를 입력해주세요.";
    private static final int INPUT_START_RANGE = 1;
    private static final int MANAGE_MENU_END_RANGE = 3;
    private static final int MANAGE_STUDENT_END_RANGE = 5;
    private static final int MANAGE_SCORE_END_RANGE = 4;
    private static final int STATE_END_RANGE = 3;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[가-힣]{2,10}$");
    CampManageService campManageService = new CampManageService();


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


    public String checkSameName(Student student) {
        while (true) {
            try {
                String input = readUserNameInput();
                validateName(input);
                validateSameName(input, student);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "[ERROR] 잘못된 입력입니다. 수정 전 이름과 다르게 입력하거나, 공백을 제외한 2글자 이상 10글자 이하로 입력해주세요");
            }
        }
    }

    public Student checkIdStudent() {
        while (true) {
            try {
                int id = readUserInput();
                validateManageStudentRange(id);
                return StudentList.getInstance().checkStudent(id);
            } catch (IllegalArgumentException e) {
                System.out.println(UPDATE_INPUT_MESSAGE);
            }
        }
    }

    public int selectNumber() {
        while (true) {
            try {
                return validateUpdateSelectRange(readUserInput());
            } catch (IllegalArgumentException e) {
                System.out.println(INPUT_ERROR_MESSAGE);
            }
        }
    }

    public int selectStateNumber(Student student) {
        while (true) {
            try {
                int ordinal = readUserInput();
                validateCheckSameState(student, ordinal);
                return validateUpdateSelectRange(ordinal);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다. 수정 전 상태와 다르게 입력해주세요.");
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

    private void validateSameName(String input, Student student) {
        if (student.getName().equals(input)) {
            throw new IllegalArgumentException();
        } else if (!NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private int validateUpdateSelectRange(int updateSelectNumber) {
        if (INPUT_START_RANGE <= updateSelectNumber
                && updateSelectNumber <= MANAGE_MENU_END_RANGE) {
            return updateSelectNumber;
        }
        throw new IllegalArgumentException();
    }

    private void validateCheckSameState(Student student, int ordinal) {
        if (student.getState().ordinal() + INPUT_START_RANGE == ordinal) {
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


}
