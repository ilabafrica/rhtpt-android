package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Field {
    long id;
    int sId;
    String title;
    int tag;

    public final static int CHECKBOX = 1;
    public final static int DATE = 2;
    public final static int EMAIL = 3;
    public final static int FIELD = 4;
    public final static int RADIO = 5;
    public final static int SELECT = 6;
    public final static int TEXT = 7;

    // constructors
    public Field(long id, int sId, String title, int tag){
        this.id = id;
        this.sId = sId;
        this.title = title;
        this.tag = tag;
    }
    public Field() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public int getsId() {
        return this.sId;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTag() {
        return this.tag;
    }
}