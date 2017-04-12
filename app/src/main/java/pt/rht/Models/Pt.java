package pt.rht.Models;

/**
 * Created by Kitsao on 23/03/2017.
 */

public class Pt {
    long id;
    int rId;
    String created_at;
    String updated_at;

    // constructors
    public Pt(long id, int rId, String created_at, String updated_at){
        this.id = id;
        this.rId = rId;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Pt() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }

    // getters
    public long getId() {
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