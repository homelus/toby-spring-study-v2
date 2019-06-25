package jun.spring.mvc.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author playjun
 * @since 2019 06 10
 */
public enum Grade {

    A(1, "최우수"),
    B(2, "우수"),
    C(3, "보통"),
    D(4, "별로");

    Grade(int no, String desc) {
        this.no = no;
        this.desc = desc;
    }

    private static final Map<Integer, Grade> BY_GRADE = new HashMap<>();

    static {
        for (Grade grade : values()) {
            BY_GRADE.put(grade.no, grade);
        }
    }

    public static Grade valueOfGrade(int no) {
        return BY_GRADE.get(no);
    }

    int no;

    String desc;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
