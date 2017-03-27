package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Enrolment {
    int id;
    int userId;
    String facility;
    String mfl;
    String program;
    String designation;
    String addFailure;
    String comment;
    String created_at;
    String updated_at;

    // constructors
    public Enrolment() {
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public void setMfl(String mfl) {
        this.mfl = mfl;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setAddFailure(String addFailure) {
        this.addFailure = addFailure;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public int getUserId() {
        return this.userId;
    }

    public String setFacility() {
        return this.facility;
    }

    public String setMfl() {
        return this.mfl;
    }

    public String setProgram() {
        return this.program;
    }

    public String setDesignation() {
        return this.designation;
    }

    public String setAddFailure() {
        return this.addFailure;
    }

    public String getComment() {
        return this.comment;
    }

    public String getCreatedAt(){
        return this.created_at;
    }

    public String getUpdatedAt(){
        return this.updated_at;
    }
}