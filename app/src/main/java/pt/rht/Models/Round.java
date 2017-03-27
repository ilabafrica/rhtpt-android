package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Round {
    int id;
    String title;
    String starts;
    String ends;

    // constructors
    public Round() {
    }
    // setters
    public void setId(int id) {
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
    public int getId() {
        return this.id;
    }

    public String setTitle() {
        return this.title;
    }

    public String setStarts() {
        return this.starts;
    }

    public String setEnds() {
        return this.ends;
    }
}