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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        try {
            campManageService.handleScoreCreation(subjectScore, inputSubjectScore, grade);
            outputView.createScoreComplete(student, selectedSubject, inputSubjectScore, emptyRound,
                grade);
        } catch (RuntimeException e) {
            outputView.roundSizeError();
        }

    }


    // 점수 조회
    private void readScore() {
        outputView.manageReadScore();
        int manageReadScore = inputView.manageReadScore();
        switch (manageReadScore) {
            case 1 -> System.out.println("수강생의 특정 과목 회차별 등급을 조회");
            case 2 -> readStudentByState();
            case 3 -> readAvgGrade();
            case 4 -> outputView.backToManageMenu();
        }
    }

    /**
     * 점수 조회
     *
     * @author 송선호
     */
    private void readStudentByState() {
        if (StudentList.getInstance().checkStudentScoreIsEmpty()) {
            outputView.checkStudentScoreIsEmpty();
        } else {
            outputView.readStudentByState();
            State state = inputView.selectState();
            List<Student> students = StudentList.getInstance().getStudentBySpecificState(state);
            outputView.readStudentBySpecificState(students);
        }
    }

    /**
     * 점수 조회(수강생의 과목별 평균 등급 조회)
     *
     * @author 유경진
     */
    private void readAvgGrade() {
        outputView.inputStudentId();
        int id = inputView.deleteStudentId();
        // 서비스
        Student student = campManageService.getStudentByStudentId(id);
        //학생정보 출력
        outputView.showAvgStudent(student);
        // 서비스
        for (int i = 0; i < student.getSubject().size(); i++) {
            int subjectId = student.getSubject().get(i).ordinal();       //과목.ordinal
            int scoreIndex = campManageService.getScoreIndex(student, subjectId);   //그 과목 인덱스 찾기
            if (scoreIndex < 10) {
                System.out.println(student.getSubject().get(i).getName() + " | "
                    + campManageService.getSubjectGrade(student,
                    campManageService.avgScore(student, scoreIndex), i));
            } else {
                System.out.println(student.getSubject().get(i).getName() + " | 점수가 없습니다.");
            }
        }
    }

    /**
     * 점수 수정
     *
     * @author 유경진
     */
    private void updateScore() {
        outputView.inputStudentId();
        int id = inputView.deleteStudentId();
        // 서비스
        Student student = campManageService.getStudentByStudentId(id);
        //과목입력
        outputView.subjectSelect(student);
        int subjectIndex = inputView.subjectSelect(student.getSubject().size()); //Subject에 들어온순서
        int subjectId = student.getSubject().get(subjectIndex).ordinal();       //과목.ordinal
        int scoreIndex = campManageService.getScoreIndex(student, subjectId);   //그 과목 인덱스 찾기
        if (scoreIndex == 10) { // ordinal은 10을 넘을 수 없기에 초기값을 10으로 설정해뒀습니다.
            System.out.println("찾을 수 없습니다.");
        } else {
            int roundSize = student.getScores().get(scoreIndex).getScorePerRound().size();
            //회차입력
            outputView.roundSelect(student, scoreIndex, subjectIndex);
            int subjectRound = inputView.roundSelect(roundSize);

            //점수입력
            outputView.updateScore(student, subjectIndex, subjectRound, scoreIndex);
            int subjectScore = inputView.inputScore(); //새로받은 점수
            SubjectGrade grade = campManageService.getSubjectGrade(student, subjectScore,
                subjectIndex);
            //점수 수정
            student.getScores().get(scoreIndex)
                .setScorePerRound(subjectRound, subjectScore, subjectId, grade);

            //완...료
            outputView.successScore(student, subjectIndex, subjectRound, subjectScore);
        }
    }
}

