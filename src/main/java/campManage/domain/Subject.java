package campManage.domain;

public enum Subject {
    JAVA("Java", SubjectCategory.REQUIRE),
    OOP("객체지향", SubjectCategory.REQUIRE),
    SPRING("Spring", SubjectCategory.REQUIRE),
    JPA("JPA",SubjectCategory.REQUIRE),
    MYSQL("MYSQL", SubjectCategory.REQUIRE),

    DESIGN_PATTERN("디자인 패턴", SubjectCategory.OPTIONAL),
    SPRING_SECURITY("Spring Security", SubjectCategory.OPTIONAL),
    REDIS("Redis", SubjectCategory.OPTIONAL),
    MONGO_DB("Mongo DB", SubjectCategory.OPTIONAL);

    private final String name;
    private final SubjectCategory subjectCategory;

    Subject(String name, SubjectCategory subjectCategory) {
        this.name = name;
        this.subjectCategory = subjectCategory;
    }

    public String getName() {
        return name;
    }

    public SubjectCategory getSubjectCategory() {
        return subjectCategory;
    }
}
