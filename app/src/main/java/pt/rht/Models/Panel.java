package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Panel {
    int id;
    int rId;
    String rDate;
    int panels;
    String receiver;

    // constructors
    public Panel() {
    }
    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setrId(String title) {
        this.rId = rId;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public void setPanels(int panels) {
        this.panels = panels;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public int getrId() {
        return this.rId;
    }

    public String getrDate() {
        return this.rDate;
    }

    public int getPanels() {
        return this.panels;
    }

    public String getReceiver() {
        return this.receiver;
    }
}