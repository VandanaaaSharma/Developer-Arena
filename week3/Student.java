public class Student {
    // Attributes (private for encapsulation)
    private String name;
    private int rollNo;
    private String grade;

    // Constructor
    public Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    // Getter and Setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    // Method
    public void displayInfo() {
        System.out.println("Name: " + name + ", Roll No: " + rollNo + ", Grade: " + grade);
    }

    // Simple runner for testing
    public static void main(String[] args) {
        Student s = new Student("Alice", 1, "A");
        s.displayInfo();
    }
}

