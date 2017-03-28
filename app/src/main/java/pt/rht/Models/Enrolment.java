package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Enrolment {
    long id;
    int userId;
    int facility;
    int mfl;
    int program;
    int designation;
    int addFailure;
    String comment;
    String created_at;
    String updated_at;

    // constructors
    public Enrolment(long id, int userId, int facility, int mfl, int program, int designation, int addFailure, String comment, String created_at, String updated_at){
        this.id = id;
        this.userId = userId;
        this.facility = facility;
        this.mfl = mfl;
        this.program = program;
        this.designation = designation;
        this.addFailure = addFailure;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Enrolment() {
    }

    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    public void setMfl(int mfl) {
        this.mfl = mfl;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    public void setDesignation(int designation) {
        this.designation = designation;
    }

    public void setAddFailure(int addFailure) {
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
    public long getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getFacility() {
        return this.facility;
    }

    public int getMfl() {
        return this.mfl;
    }

    public int getProgram() {
        return this.program;
    }

    public int getDesignation() {
        return this.designation;
    }

    public int getAddFailure() {
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