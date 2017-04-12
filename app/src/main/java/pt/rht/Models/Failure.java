package pt.rht.Models;

/**
 * Created by Kitsao on 23/03/2017.
 */

public class Failure {
    long id;
    String title;

    // constructors
    public Failure(long id, String title){
        this.id = id;
        this.title = title;
    }
    public Failure() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}