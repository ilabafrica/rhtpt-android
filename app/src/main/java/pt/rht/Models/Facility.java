package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Facility {
    long id;
    String name;
    int mfl;

    // constructors
    public Facility(long id, String name, int mfl){
        this.id = id;
        this.name = name;
        this.mfl = mfl;
    }
    public Facility() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMfl(int mfl) {
        this.mfl = mfl;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getMfl() {
        return this.mfl;
    }
}