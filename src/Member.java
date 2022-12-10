import java.io.Serializable;

/**
 * Member model: id, name.
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public String name;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
