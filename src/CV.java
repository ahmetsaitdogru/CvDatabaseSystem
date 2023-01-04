import java.util.Scanner;
public class CV<AnyType> {

    Scanner scanner=new Scanner(System.in);

    private String name;
    private int ID;
    private String skill;
    private String experience;
    private String education;

    public String address;


    public void setName(String name){
        this.name=name;
    }
    public String getName() {return name;}

    public void setID(int id){
        if (ID<1)
            ID=1;
        this.ID=id;
    }
    public int getID() {return ID;}

    private void setSkill(String skill){
        this.skill=skill;
    }
    private String getSkill() {return skill;}

    public void setEducation(String education) {
        this.education = education;
    }
    public String getEducation() {return education;}

    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getExperience() {return experience;}

    public String getAddress() {return address;}
    public void setAddress(String address) {
        this.address = address;
    }



    public CV(){
       setName("Blank");
       setID(1);
       setSkill("Blank");
       setEducation("Blank");
       setExperience("Blank");
       setAddress("b");
    }

    public CV(String name, int id, String skill, String education, String experience){
        name=getName();
        id=getID();
        skill=getSkill();
        education=getEducation();
        experience=getExperience();
        address=getAddress();
    }

    public void createCV(){
        String name1; int id1; String skill1; String education1; String experience1; String ad1;

        System.out.println("Please enter name:");
        name1=scanner.next();
        setName(name1);

        System.out.println("Please enter id:");
        id1=scanner.nextInt();
        setID(id1);

        System.out.println("Please enter skill:");
        skill1=scanner.next();
        setSkill(skill1);

        System.out.println("Please enter education:");
        education1=scanner.next();
        setEducation(education1);

        System.out.println("Please enter experience:");
        experience1=scanner.next();
        setExperience(experience1);

        System.out.println("Please enter address:");
        ad1=scanner.next();
        setAddress(ad1);
    }

    public CV[] array;

    private void setArray(CV[] array){
        this.array=array;
    }
    private CV[] getArray(){return array;}

    public void createArray(){
        setArray(array);
    }
}
