package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Result {
    long id;
    int ptId;
    int fId;
    String response;
    String comment;

    // constructors
    public Result(long id, int ptId, int fId, String response, String comment){
        this.id = id;
        this.ptId = ptId;
        this.fId = fId;
        this.response = response;
        this.comment = comment;
    }
    public Result() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setPtId(int ptId) {
        this.ptId = ptId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public int getPtId() {
        return this.ptId;
    }

    public int getfId() {
        return this.fId;
    }

    public String getResponse() {
        return this.response;
    }

    public String getComment() {
        return this.comment;
    }
}