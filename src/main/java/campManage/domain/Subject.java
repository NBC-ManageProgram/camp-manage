package campManage.domain;

public enum Subject {
    JAVA("Java", SubjectCategory.REQUIRE),
    OOP("객체지향", SubjectCategory.REQUIRE),
    SPRING("Spring", SubjectCategory.REQUIRE),
    JPA("JPA", SubjectCategory.REQUIRE),
    MYSQL("MYSQL", SubjectCategory.REQUIRE),

    DESIGN_PATTERN("디자인 패턴", SubjectCategory.OPTIONAL),
    SPRING_SECURITY("Spring Security", SubjectCategory.OPTIONAL),
    REDIS("Redis", SubjectCategory.OPTIONAL),
    MONGO_DB("Mongo DB", SubjectCategory.OPTIONAL);


    private static final int REQUIRE_SUBJECT_INDEX = 1;
    private static final int OPTIONAL_SUBJECT_INDEX = 4;
    private final String name;
    private final SubjectCategory subjectCategory;

    Subject(String name, SubjectCategory subjectCategory) {
        this.name = name;
        this.subjectCategory = subjectCategory;
    }

    public static Subject getRequireSubjectByOrdinal(int ordinal) {
        for (Subject subject : Subject.values()) {
            if (subject.ordinal() + REQUIRE_SUBJECT_INDEX == ordinal) {
                return subject;
            }
        }
        throw new IllegalArgumentException();
    }

    public static Subject getOptionalSubjectByOrdinal(int ordinal) {
        for (Subject subject : Subject.values()) {
            if (subject.ordinal() == ordinal + OPTIONAL_SUBJECT_INDEX) {
                return subject;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getName() {
        return name;
    }





}
