package model;

public class feedback {

    private String uid;
    private String feedback;


    public feedback(String uid, String feedback) {
        this.uid = uid;
        this.feedback = feedback;
    }

    public String getUid() {
        return uid;
    }

    public String getFeedback() {
        return feedback;
    }
}
