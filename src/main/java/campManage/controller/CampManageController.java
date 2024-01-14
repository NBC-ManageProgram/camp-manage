package campManage.controller;

import campManage.domain.Score;
import campManage.domain.State;
import campManage.domain.Student;
import campManage.domain.StudentList;
import campManage.domain.Subject;
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

        System.out.println(name);

        outputView.createRequireSubject();
        List<Subject> requireSubjects = inputView.requireSubject();

        outputView.createOptionalSubject();
        List<Subject> optionalSubjects = inputView.optionalSubject();

        outputView.createState();

        campManageService.createStudent(name, requireSubjects, optionalSubjects, inputView.state());
    }

    // 수강생 조회
    private void readStudent() {
        StudentList.getInstance().getStudents();
    }

    /**
     * 수강생 수정
     *
     * @author 송선호
     */
    private void updateStudent() {

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
        //서비스
        Student student = campManageService.getStudentByStudentId(studentID);


        // 과목 입력하세요 출력
        outputView.ShowStudentName(student,student.getSubject());
        //점수 입력할 과목 선택받기 student에 있는 인덱스 번호 (선택번호 -1)
        int selectedSubject = inputView.SelectedSubject(student.getSubject().size());
        //점수 입력하세요 출력

        outputView.createScore(student,selectedSubject);


        int subjectScore = inputView.inputPerScore();
        List<Integer> perScore = new ArrayList<>();
        perScore.add(subjectScore);
        student.addScore(new Score(selectedSubject,perScore));






        //등록 완료
        OutputView.createScoreComplete(student,selectedSubject,subjectScore);



    }

    // 점수 조회
    private void readScore() {

    }

    /**
     * 점수 수정
     *
     * @author 유경진
     */
    private void updateScore() {

    }


}
