package pt.rht.Models;

/**
 * Created by Kitsao on 23/03/2017.
 */

public class Round {
    long id;
    String title;
    String starts;
    String ends;

    // constructors
    public Round(long id, String title, String starts, String ends){
        this.id = id;
        this.title = title;
        this.starts = starts;
        this.ends = ends;
    }
    public Round() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStarts() {
        return this.starts;
    }

    public String getEnds() {
        return this.ends;
    }
}