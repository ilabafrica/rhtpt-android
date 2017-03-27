package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Failure {
    int id;
    String title;

    // constructors
    public Failure() {
    }
    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}