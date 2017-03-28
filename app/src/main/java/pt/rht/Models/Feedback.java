package pt.rht.Models;

/**
 * Created by ilabafrica on 23/03/2017.
 */

public class Feedback {
    long id;
    String round;
    String feedback;
    String comment;
    String created_at;

    // constructors
    public Feedback(long id, String round, String feedback, String comment, String created_at){
        this.id = id;
        this.round = round;
        this.feedback = feedback;
        this.comment = comment;
        this.created_at = created_at;
    }
    public Feedback() {
    }
    // setters
    public void setId(long id) {
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

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    // getters
    public long getId() {
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

    public String getCreatedAt() {
        return this.created_at;
    }
}