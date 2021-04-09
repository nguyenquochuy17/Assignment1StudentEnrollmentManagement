package rmit.p1;

public class Student {
    private String id;
    private String name;
    private String birthday;

    public Student(String id, String name, String birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj instanceof Student)
        {
            Student temp = (Student) obj;
            if(this.getId() == temp.getId() && this.getName()== temp.getName() && this.getBirthday() == temp.getBirthday())
                return true;
        }
        return false;

    }
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub

        return (this.getId().hashCode() + this.getName().hashCode() + this.getBirthday().hashCode());
    }
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
