package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Feedback {
    int id;
    String round;
    String feedback;
    String comment;
    String created_at;

    // constructors
    public Feedback() {
    }
    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setComment(String title) {
        this.comment = comment;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getRound() {
        return this.round;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public String getComment() {
        return this.comment;
    }

    public String getCreated_at() {
        return this.created_at;
    }
}