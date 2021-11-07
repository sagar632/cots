package model;

public class applied {

    private String emailid;
    private String status;
    private String fullname;
    private String rollno;
    private String phonenom;


    public String getFullname() {
        return fullname;
    }

    public String getRollno() {
        return rollno;
    }

    public String getPhonenom() {
        return phonenom;
    }

    public applied(String emailid, String status, String fullname, String rollno, String phonenom) {
        this.emailid = emailid;
        this.status = status;
        this.fullname = fullname;
        this.rollno = rollno;
        this.phonenom = phonenom;
    }

    public applied() {
    }

    public applied(String emailid, String status) {
        this.emailid = emailid;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public applied(String emailid) {

        this.emailid = emailid;
    }



    public String getEmailid() {
        return emailid;
    }
}
