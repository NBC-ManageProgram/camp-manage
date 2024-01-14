package campManage.view;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import campManage.service.CampManageService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class InputView {

    private static final String INPUT_ERROR_MESSAGE = "[ERROR] 잘못된 값을 입력하였습니다. 다시 입력해주세요.";
    private static final String NAME_ERROR_MESSAGE = "[ERROR] 잘못된 입력입니다. 공백을 제외한 2글자 이상 10글자 이하의 한글로 입력해 주세요.";

    private static final String REQUIRE_SUBJECT_ERROR_MESSAGE =
            "[ERROR] 잘못된 입력입니다. 1 이상 5 이하의 정수로 서로 다른 3개 이상의 필수 과목을 선택해주세요.\n" +
                    "각 과목은 공백으로 구분되어야 합니다.";

    private static final String OPTIONAL_SUBJECT_ERROR_MESSAGE =
            "[ERROR] 잘못된 입력입니다. 1 이상 4 이하의 정수로 서로 다른 2개 이상의 선택 과목을 선택해주세요.\n" +
                    "각 과목은 공백으로 구분되어야 합니다.";

    private static final String STATE_ERROR_MESSAGE = "[ERROR] 잘못된 입력입니다. 1 이상 3 이하의 정수로 입력해주세요.";
    private static final String UPDATE_INPUT_MESSAGE = "[ERROR] 잘못된 입력입니다. 유효한 고유번호를 입력해주세요.";

    private static final int INPUT_START_RANGE = 1;
    private static final int MANAGE_MENU_END_RANGE = 3;
    private static final int MANAGE_STUDENT_END_RANGE = 5;
    private static final int MANAGE_SCORE_END_RANGE = 4;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[가-힣]{2,10}$");
    private static final Pattern REQUIRE_SUBJECT_PATTERN = Pattern.compile("([1-5]\\s){2,}[1-5]");
    private static final Pattern OPTIONAL_SUBJECT_PATTERN = Pattern.compile("([1-4]\\s){1,}[1-5]");

    private <T> T getInputWithValidation(Function<Integer, T> function, String errorMessage) {
        while (true) {
            try {
                int input = readUserInput();
                return function.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage);
            }
        }
    }

    public List<Subject> readSubjects(Consumer<String> consumer,
            Function<Integer, Subject> function, String errorMessage) {
        while (true) {
            List<Subject> subjects = new ArrayList<>();
            try {
                String input = readLineInput();
                consumer.accept(input);

                List<String> subjectNumbers = Arrays.stream(input.split(" ")).toList();
                validateIsDuplicate(subjectNumbers);

                for (String subjectNumber : subjectNumbers) {
                    subjects.add(function.apply(Integer.parseInt(subjectNumber)));
                }
                return subjects;
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private int validateRange(int inputValue, int endRange) {
        if (INPUT_START_RANGE <= inputValue && inputValue <= endRange) {
            return inputValue;
        }
        throw new IllegalArgumentException();
    }

    private void validatePattern(Pattern pattern, String input) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public int manageMenu() {
        return getInputWithValidation(this::validateManageMenuRange, INPUT_ERROR_MESSAGE);
    }

    public int manageStudent() {
        return getInputWithValidation(this::validateManageStudentRange, INPUT_ERROR_MESSAGE);
    }

    public int manageScore() {
        return getInputWithValidation(this::validateManageScoreRange, INPUT_ERROR_MESSAGE);
    }


    public State state() {
        Function<Integer, State> stateMapper = State::getStateByInput;
        return getInputWithValidation(stateMapper, STATE_ERROR_MESSAGE);
    }


    public String name() {
        while (true) {
            try {
                String input = readLineInput();
                validateName(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(NAME_ERROR_MESSAGE);
            }
        }
    }

    public List<Subject> requireSubject() {
        Function<Integer, Subject> subjectMapper = Subject::getRequireSubjectByOrdinal;
        return readSubjects(this::validateRequireSubject, subjectMapper,
                REQUIRE_SUBJECT_ERROR_MESSAGE);
    }

    public List<Subject> optionalSubject() {
        Function<Integer, Subject> subjectMapper = Subject::getOptionalSubjectByOrdinal;
        return readSubjects(this::validateOptionalSubject, subjectMapper,
                OPTIONAL_SUBJECT_ERROR_MESSAGE);
    }


    public String checkSameName(Student student) {
        while (true) {
            try {
                String input = readLineInput();
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
        return validateRange(manageMenuNumber, MANAGE_MENU_END_RANGE);
    }

    private int validateManageStudentRange(int manageStudentNumber) {
        return validateRange(manageStudentNumber, MANAGE_STUDENT_END_RANGE);
    }

    private int validateManageScoreRange(int manageScoreNumber) {
        return validateRange(manageScoreNumber, MANAGE_SCORE_END_RANGE);
    }

    private void validateIsDuplicate(List<String> subjectNumbers) {
        if (!(subjectNumbers.stream().distinct().count() == subjectNumbers.size())) {
            throw new IllegalArgumentException();
        }
    }


    private void validateName(String input) {
        validatePattern(NAME_PATTERN, input);
    }

    private void validateRequireSubject(String input) {
        validatePattern(REQUIRE_SUBJECT_PATTERN, input);
    }

    private void validateOptionalSubject(String input) {
        validatePattern(OPTIONAL_SUBJECT_PATTERN, input);
    }

    private String readLineInput() {
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
    public int readStudent() {
        while (true) {
            try {
                int readChoice = readUserInput();
                if (readChoice == 1 || readChoice == 2 || readChoice == 3) {
                    return readChoice;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다. 1 이상 3 이하의 정수로 입력해주세요.");
            }
        }
    }

    private void validateSameName(String input, Student student) {
        if (student.getName().equals(input)) {
            throw new IllegalArgumentException();
        } else if (!NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCheckSameState(Student student, int ordinal) {
        if (student.getState().ordinal() + 1 == ordinal) {
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
}
