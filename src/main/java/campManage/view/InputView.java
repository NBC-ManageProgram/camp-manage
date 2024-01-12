package campManage.view;

import campManage.domain.Subject;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.regex.Pattern;

public class InputView {
    private static final String INPUT_ERROR_MESSAGE = "[ERROR] 잘못된 값을 입력하였습니다. 다시 입력해주세요.";
    private static final int INPUT_START_RANGE = 1;
    private static final int MANAGE_MENU_END_RANGE = 3;



    public int manageMenu() {
        return getInputWithValidation(this::readUserInput, this::validateManageMenuRange);
    }

    private <T>T getInputWithValidation(IntSupplier inputSupplier, Function<Integer, T> function) {
        while (true) {
            try {
                int input = inputSupplier.getAsInt();
                return function.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(INPUT_ERROR_MESSAGE);
            }
        }
    }


    private int validateManageMenuRange(int manageMenuNumber) {
        if (INPUT_START_RANGE <= manageMenuNumber && manageMenuNumber <= MANAGE_MENU_END_RANGE) {
            return manageMenuNumber;
        }
        throw new IllegalArgumentException();
    }

    private int readUserInput() {
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

}
