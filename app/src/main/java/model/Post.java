package model;

public class Post {

private String date;
    private String descri;
    private String imageuri;
    private String time;
private String postname;
private String poster_profile_image;

    public Post(String date, String descri, String imageuri, String time, String postname, String poster_profile_image) {
        this.date = date;
        this.descri = descri;
        this.imageuri = imageuri;
        this.time = time;
        this.postname = postname;
        this.poster_profile_image = poster_profile_image;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPoster_profile_image() {
        return poster_profile_image;
    }

    public Post(String date, String descri, String imageuri, String time, String postname) {
        this.date = date;
        this.descri = descri;
        this.imageuri = imageuri;
        this.time = time;
        this.postname = postname;

    }

    public String getPostname() {
        return postname;
    }

  /*  public Post(String date, String descri, String imageuri, String time) {
        this.date = date;
        this.descri = descri;
        this.imageuri = imageuri;
        this.time = time;
    }*/

    public String getTime() {
        return time;
    }

    public Post() {
    }

 /*   public Post(String date, String descri, String imageuri) {
        this.date = date;
        this.descri = descri;
        this.imageuri = imageuri;
    }*/

    public String getDate() {
        return date;
    }

 /*   public Post(String descri, String imageuri) {
        this.descri = descri;
        this.imageuri = imageuri;
    }
*/
    public String getDescri() {
        return descri;
    }

    public String getImageuri() {
        return imageuri;
    }
}
