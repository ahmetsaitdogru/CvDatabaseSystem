import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class User {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;
    private List<CV> cvs;
    private List<CV> taggedCVs;
    private List<CV> bin;

    public User(String name, String surname, String email, String phoneNumber, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cvs = new ArrayList<CV>();
        this.taggedCVs = new ArrayList<CV>();
        this.bin = new ArrayList<CV>();
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }


    public void addCV(CV cv) {
        // Add the CV to the alphabetical list of CVs
        this.cvs.add(cv);

        // Sort the list of CVs alphabetically by the applicant's name
        Collections.sort(this.cvs, new Comparator<CV>() {
            @Override
            public int compare(CV cv1, CV cv2) {
                return cv1.getName().compareTo(cv2.getName());
            }
        });
    }

    public void tagCV(CV cv) {
        // Remove the CV from the alphabetical list of CVs
        this.cvs.remove(cv);

        // Add the CV to the list of tagged CVs
        this.taggedCVs.add(cv);

        // Sort the list of tagged CVs by the applicant's name
        Collections.sort(this.taggedCVs, new Comparator<CV>() {
            @Override
            public int compare(CV cv1, CV cv2) {
                return cv1.getName().compareTo(cv2.getName());
            }
        });
    }

    public void removeCV(CV cv) {
        // Remove the CV from the alphabetical list of CVs
        this.cvs.remove(cv);

        // Remove the CV from the list of tagged CVs if it is present
        this.taggedCVs.remove(cv);

        // Add the CV to the bin
        this.bin.add(cv);
    }

    public void editCV(CV cv, String newContent) {
        // Update the content of the CV
        cv.setEducation(newContent);
        cv.setExperience(newContent);
    }

    public List<CV> getCVs() {
        return this.cvs;
    }

    public List<CV> getTaggedCVs() {
        return this.taggedCVs;
    }

    public List<CV> getBin() {
        return this.bin;
    }
}

