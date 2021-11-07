package model;

public class profile_image  {

private String imageur;

    public profile_image(String imageur) {
        this.imageur = imageur;
    }

   /* public profile_image(String email, String fname, String last_name, String password, String phone_number, String roll_no, String imageur) {
        super(email, fname, last_name, password, phone_number, roll_no);
        this.imageur = imageur;
    }*/


    public profile_image() {
    }

    public String getImageur() {
        return imageur;
    }
}
