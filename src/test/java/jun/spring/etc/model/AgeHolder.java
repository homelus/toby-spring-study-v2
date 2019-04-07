package jun.spring.etc.model;

public interface AgeHolder {

    default int age() {
        return getAge();
    }

    int getAge();

    void setAge(int age);

}
