package campManage.controller;

import campManage.domain.Subject;
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
        while(true) {
            selectManage();
        }
    }

    // 관리 메뉴 호출
    private void selectManage() {
        outputView.manageMenu();
        int manageMenu = inputView.manageMenu();

        switch (manageMenu){
            case 1 -> manageStudent(); // 1. 수강생 관리
            case 2 -> manageScore(); // 2. 점수 관리
            case 3 -> System.exit(0);
        }
    }


    // 1. 수강생 관리
    private void manageStudent() {
        outputView.manageStudent();
        int manageStudent = inputView.manageStudent();

        switch (manageStudent){
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
     * @author 손준형 */
    private void createStudent() {
//        outputView.createStudent();
//        String name = inputView.name();
//
//        System.out.println(name);
//
//        outputView.createRequireSubject();
//        List<Subject> requireSubjects = inputView.requireSubject();
//
//        outputView.createOptionalSubject();
//        List<Subject> optionalSubjects = inputView.optionalSubject();
//
//        outputView.createState();
//
//        Student student = campManageService.createStudent(name, requireSubjects, optionalSubjects, inputView.state());
    }

    // 수강생 조회
    private void readStudent() {

    }

    /**
     * 수강생 수정
     *
     * @author 송선호 */
    private void updateStudent() {

    }

    /**
     * 수강생 삭제
     *
     * @author 전석배 */
    private void deleteStudent() {

    }


    // 2. 점수 관리
    private void manageScore() {
        outputView.manageScore();
        int manageScore = inputView.manageScore();

        switch (manageScore){
            case 1 -> createScore();
            case 2 -> readScore();
            case 3 -> updateScore();
            case 4 -> outputView.backToManageMenu();
        }
    }

    /**
     * 점수 등록
     *
     * @author 이도연 */
    private void createScore() {

    }

    // 점수 조회
    private void readScore() {

    }

    /**
     * 점수 수정
     *
     * @author 유경진 */
    private void updateScore() {

    }


}
