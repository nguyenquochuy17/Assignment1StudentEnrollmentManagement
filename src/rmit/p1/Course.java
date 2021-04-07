package rmit.p1;

public class Course {
    private String id;
    private String name;
    private int numOfCredit;

    public Course(String id, String name, int numOfCredit) {
        this.id = id;
        this.name = name;
        this.numOfCredit = numOfCredit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumOfCredit() {
        return numOfCredit;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumOfCredit(int numOfCredit) {
        this.numOfCredit = numOfCredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numOfCredit=" + numOfCredit +
                '}';
    }
}
