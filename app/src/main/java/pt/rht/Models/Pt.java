package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Pt {
    int id;
    int rId;
    String created_at;
    String updated_at;

    // constructors
    public Pt() {
    }
    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setrId(String title) {
        this.rId = rId;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public int getrId() {
        return this.rId;
    }

    public String getCreatedAt(){
        return this.created_at;
    }

    public String getUpdatedAt(){
        return this.updated_at;
    }
}