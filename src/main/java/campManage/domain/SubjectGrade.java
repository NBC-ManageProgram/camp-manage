package campManage.domain;

public enum SubjectGrade {
    A,
    B,
    C,
    D,
    F,
    N;

    //필수과목 grade 반환 함수
    public static SubjectGrade requireGetGrade(int score) {
        if (score >= 95) {
            return A;
        } else if (score >= 90) {
            return B;
        } else if (score >= 80) {
            return C;
        } else if (score >= 70) {
            return D;
        } else if (score >= 60) {
            return F;
        } else {
            return N;
        }
    }

    //선택과목 grade 반환 함수
    public static SubjectGrade optionGetGrade(int score) {
        if (score >= 90) {
            return A;
        } else if (score >= 80) {
            return B;
        } else if (score >= 70) {
            return C;
        } else if (score >= 60) {
            return D;
        } else if (score >= 50) {
            return F;
        } else {
            return N;
        }
    }


}
