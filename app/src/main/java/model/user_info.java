package model;

import android.support.design.widget.FloatingActionButton;

public class user_info {

private String email;
    private String fname;
    private String last_name;
    private String password;
    private String phone_number;
    private String roll_no;




    public user_info() {
    }

    public user_info(String email, String fname, String last_name, String password, String phone_number, String roll_no) {
        this.email = email;
        this.fname = fname;
        this.last_name = last_name;
        this.password = password;
        this.phone_number = phone_number;
        this.roll_no = roll_no;
    }


    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getRoll_no() {
        return roll_no;
    }
}
