package campManage;

import campManage.controller.CampManageController;

public class Application {
    public static void main(String[] args) {
        CampManageController campManageController = new CampManageController();
        campManageController.start();

    }
}