package model;

public class events {


    private String flex;
    private String evetitle;
    private String eveobjective;
    private String eveorganized;
    private String evefaculty;
    private String evedate;
    private String evetime;
    private String eveduration;
    private String evevenue;
    private String evetrainer;
    private String evecoordinator;
    private String evesubcoordinator;
    private String fee;
    private String evedescri;
    private String phoneno;

    public events() {
    }

    public events(String flex, String evetitle, String eveobjective, String eveorganized, String evefaculty, String evedate, String evetime, String eveduration, String evevenue, String evetrainer, String evecoordinator, String evesubcoordinator, String fee, String evedescri, String phoneno) {
        this.flex = flex;
        this.evetitle = evetitle;
        this.eveobjective = eveobjective;
        this.eveorganized = eveorganized;
        this.evefaculty = evefaculty;
        this.evedate = evedate;
        this.evetime = evetime;
        this.eveduration = eveduration;
        this.evevenue = evevenue;
        this.evetrainer = evetrainer;
        this.evecoordinator = evecoordinator;
        this.evesubcoordinator = evesubcoordinator;
        this.fee = fee;
        this.evedescri = evedescri;
        this.phoneno = phoneno;
    }

    public String getFlex() {
        return flex;
    }

    public String getEvetitle() {
        return evetitle;
    }

    public String getEveobjective() {
        return eveobjective;
    }

    public String getEveorganized() {
        return eveorganized;
    }

    public String getEvefaculty() {
        return evefaculty;
    }

    public String getEvedate() {
        return evedate;
    }

    public String getEvetime() {
        return evetime;
    }

    public String getEveduration() {
        return eveduration;
    }

    public String getEvevenue() {
        return evevenue;
    }

    public String getEvetrainer() {
        return evetrainer;
    }

    public String getEvecoordinator() {
        return evecoordinator;
    }

    public String getEvesubcoordinator() {
        return evesubcoordinator;
    }

    public String getFee() {
        return fee;
    }

    public String getEvedescri() {
        return evedescri;
    }

    public String getPhoneno() {
        return phoneno;
    }
}
