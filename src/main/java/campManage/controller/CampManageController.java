package campManage.controller;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
import campManage.domain.SubjectCategory;
import campManage.domain.SubjectGrade;
import campManage.service.CampManageService;
import campManage.view.InputView;
import campManage.view.OutputView;
import java.util.List;

public class CampManageController {

    private final CampManageService campManageService;
    private final InputView inputView;
    private final OutputView outputView;

    public CampManageController() {
        this.campManageService = new CampManageService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    // 시작
    public void start() {
        while (true) {
            selectManage();
        }
    }

    // 관리 메뉴 호출
    private void selectManage() {
        outputView.manageMenu();
        int manageMenu = inputView.manageMenu();

        switch (manageMenu) {
            case 1 -> manageStudent(); // 1. 수강생 관리
            case 2 -> manageScore(); // 2. 점수 관리
            case 3 -> System.exit(0);
        }
    }

    // 1. 수강생 관리
    private void manageStudent() {
        outputView.manageStudent();
        int manageStudent = inputView.manageStudent();
        switch (manageStudent) {
            case 1 -> createStudent();
            case 2 -> readStudent();
            case 3 -> updateStudent();
            case 4 -> deleteStudent();
            case 5 -> outputView.backToManageMenu();
        }
    }

    /**
     * 수강생 등록
     *
     * @author 손준형
     */
    private void createStudent() {
        outputView.createStudent();
        String name = inputView.name();

        outputView.createRequireSubject();
        List<Subject> requireSubjects = inputView.requireSubject();

        outputView.createOptionalSubject();
        List<Subject> optionalSubjects = inputView.optionalSubject();

        outputView.createState();
        State state = inputView.state();

        campManageService.createStudent(name, requireSubjects, optionalSubjects, state);
        outputView.createComplete();
    }

    /**
     * 수강생 조회
     *
     * @author 전석배,
     */
    private void readStudent() {
        // StudentList.getInstance().getStudents();
        outputView.readStudent();
        int readChoice = inputView.readStudent();
        switch (readChoice) {
            case 1 -> getAllStudents();
            case 2 -> getStudentsByState();
            case 3 -> outputView.backToManageMenu();
        }
    }

    /**
     * 수강생 조회
     *
     * @author 전석배,
     */

    private void getAllStudents() {
        StudentList studentList = StudentList.getInstance();
        outputView.getAllStudents(studentList);
    }

    /**
     * 수강생 조회
     *
     * @author 송선호
     */
    private void getStudentsByState() {
        outputView.getStudentStateMessage();
        selectStudentByState(State.GREEN);
        selectStudentByState(State.YELLOW);
        selectStudentByState(State.RED);
    }

    /**
     * 수강생 조회
     *
     * @author 송선호
     */
    private void selectStudentByState(State state) {
        List<Student> studentsState = StudentList.getInstance().getStudentByState(state);
        outputView.getStudentStateLi(studentsState, state);
    }

    /**
     * 수강생 수정
     *
     * @author 송선호
     */
    private void updateStudent() {
        if (StudentList.getInstance().getStudentsIsEmpty()) {
            outputView.checkIsEmpty();
        } else {
            // 1. 수강생의 고유번호를 입력받는 화면 출력
            outputView.updateId();
            Student student = inputView.checkIdStudent();
            // 2. 수강생 정보 수정 리스트 출력
            outputView.updateList(student);
            int updateSelectNumber = inputView.selectNumber();
            switch (updateSelectNumber) {
                // 3. 이름 수정시 출력
                case 1 -> {
                    campManageService.updateName(student, outputView, inputView);
                    // 5. 수정 완료시 전체 정보 확인차 출력
                    outputView.updateComplete(student);
                }
                // 4. 상태 수정시 출력
                case 2 -> {
                    campManageService.updateState(student, outputView, inputView);
                    // 5. 수정 완료시 전체 정보 확인차 출력
                    outputView.updateComplete(student);
                }
                case 3 -> outputView.backToManageMenu();
            }
        }
    }


    /**
     * 수강생 삭제
     *
     * @author 전석배
     */
    private void deleteStudent() {
        // 삭제할 고유번호 입력
        outputView.deleteStudentId();
        int studentId = inputView.deleteStudentId();

        // 서비스
        Student student = campManageService.getStudentByStudentId(studentId);

        // 삭제 확인 여부 출력
        outputView.isRealDelete(student);
        int userDeleteChoice = inputView.userDeleteChoice();

        if (userDeleteChoice == 1) {
            campManageService.deleteStudent(student);
            outputView.deleteCorrect();
        }

    }


    // 2. 점수 관리
    private void manageScore() {
        outputView.manageScore();
        int manageScore = inputView.manageScore();

        switch (manageScore) {
            case 1 -> createScore();
            case 2 -> readScore();
            case 3 -> updateScore();
            case 4 -> outputView.backToManageMenu();
        }
    }

    /**
     * 점수 등록
     *
     * @author 이도연
     */
    private void createScore() {

        // 고유번호 입력하세요 출력
        outputView.inputStudentId();
        //검증한 고유번호 입력받기
        int studentID = inputView.readStudentId();
        Student student = campManageService.getStudentByStudentId(studentID);

        // 과목 입력하세요 출력
        outputView.ShowStudentName(student, student.getSubject());
        //점수 입력할 과목 선택받기
        int selectedSubject = inputView.SelectedSubject(student.getSubject().size());
        Score subjectScore = campManageService.getSubjectScore(student, selectedSubject);

        int emptyRound = subjectScore.isemptyScore();
        //점수 입력하세요 출력
        outputView.createScore(student, selectedSubject, emptyRound);
        int inputSubjectScore = inputView.inputPerScore();

        SubjectGrade grade = campManageService.getSubjectGrade(student, inputSubjectScore,
            selectedSubject);

        if (subjectScore.getScorePerRoundSize() >= 10) {
            System.out.println("[ERROR] 10회 이상의 입력은 불가능합니다.");
        } else {
            subjectScore.addScore(inputSubjectScore);
            subjectScore.addGrade(grade);
        OutputView.createScoreComplete(student, selectedSubject, inputSubjectScore, emptyRound,
            grade);
        }

    }


    // 점수 조회
    private void readScore() {
        outputView.manageReadScore();
        int manageReadScore = inputView.manageReadScore();
        switch (manageReadScore) {
            case 1 -> System.out.println("수강생의 특정 과목 회차별 등급을 조회");
            case 2 -> System.out.println("특정 상태 수강생들의 필수 과목별 평균 등급 조회");
            case 3 -> System.out.println("수강생의 과목별 평균 등급 조회");
            case 4 -> outputView.backToManageMenu();

        }

    }

    /**
     * 점수 수정
     *
     * @author 유경진
     */
    private void updateScore() {

    }


}
