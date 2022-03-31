public class Student {
    private String name;
    private int number;

    Student(String aName, int aNumber) {
        name = aName;
        number = aNumber;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int n) {
        number = n;
    }
}
class StudentTest
{
    public static void main(String[] args)
    {
        Student s = new Student("Tom",9901);
        System.out.println("I am " + s.getName() + ". My number is " + s.getNumber() );
        s.setNumber(9902);
        System.out.println("My number was changed.It is "  + s.getNumber() );
    }
}
