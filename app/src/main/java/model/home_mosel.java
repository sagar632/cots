package model;

public class home_mosel {


     private int profile_immage;
      private String profile_name;
      private String date;
    private String descri;
    private String imageuri;

    public home_mosel() {
    }

    public home_mosel(String descri, String imageuri) {
        this.descri = descri;
        this.imageuri = imageuri;
    }

    public home_mosel(  String profile_name, String date, String descri, String imageuri) {
//        this.profile_immage = profile_immage;
        this.profile_name = profile_name;
        this.date = date;
        this.descri = descri;
        this.imageuri = imageuri;
    }

    public String getDescri() {
        return descri;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public String getImageuri() {
        return imageuri;
    }
}



