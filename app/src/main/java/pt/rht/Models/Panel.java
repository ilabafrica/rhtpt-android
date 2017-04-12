package pt.rht.Models;

/**
 * Created by Kitsao on 23/03/2017.
 */

public class Panel {
    long id;
    int rId;
    String rDate;
    int panels;
    String receiver;

    // constructors
    public Panel(long id, int rId, String rDate, int panels, String receiver){
        this.id = id;
        this.rId = rId;
        this.rDate = rDate;
        this.panels = panels;
        this.receiver = receiver;
    }
    public Panel() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setrId(int rId) {
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
    public long getId() {
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