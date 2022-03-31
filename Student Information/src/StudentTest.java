class Address{
    private String streetAddress,city,state;
    private long zipCode;

    public Address(String street,String town,String st,long zip){
        streetAddress = street;
        city = town;
        state = st;
        zipCode = zip;
    }

    public String toString(){
        String result;

        result = streetAddress + "\n";
        result += city + "," + state + " " + zipCode;

        return result;
    }
}

class Student{
    private String firstName,lastName;
    private Address homeAddress,schoolAddress;

    public Student(String first,String last,Address home,Address school){
        firstName = first;
        lastName = last;
        homeAddress = home;
        schoolAddress = school;
    }

    public String toString(){
        String result;

        result = firstName + " " + lastName + "\n";
        result += "Home Address:\n" + homeAddress + "\n";
        result += "School Address:\n" + schoolAddress + "\n";

        return result;
    }
}

public class StudentTest {
    public static void main(String[] args){
        Address school = new Address("38 Zheda Rd","Hangzhou","ZJ",310027);

        Address jHome = new Address("20 Yugu Rd.","Hangzhou","ZJ",310013);

        Student john = new Student("John","Smith",jHome,school);

        Address mHome = new Address("123 Zhongshan Rd.","Fuzhou","FJ",350000);

        Student marsha = new Student("Marsha","Jones",mHome,school);

        System.out.println(john);
        System.out.println();
        System.out.println(marsha);
    }
}
