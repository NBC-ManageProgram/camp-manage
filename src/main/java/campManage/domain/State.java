package campManage.domain;

public enum State {
    GREEN,
    YELLOW,
    RED;

    private static final int STATE_INDEX = 1;

    public static State getStateByInput(int ordinal) {
        for (State state : State.values()) {
            if (state.ordinal() + STATE_INDEX == ordinal) {
                return state;
            }
        }
        throw new IllegalArgumentException();
    }

}
