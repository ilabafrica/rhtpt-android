package pt.rht.Models;

/**
 * Created by Kitsao on 23/03/2017.
 */

public class User {
    long id;
    String fullName;
    int gender;
    int uid;
    String dob;
    String mobile;
    String email;
    String username;
    String password;
    String created_at;
    String updated_at;

    // constructors
    public User(long id, String fullName, int gender, int uid, String dob, String mobile, String email, String username, String password, String created_at, String updated_at){
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.uid = uid;
        this.dob = dob;
        this.mobile = mobile;
        this.email = email;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public User() {
    }
    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    public void setUpdatedAt(String updated_at){
        this.updated_at = updated_at;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.fullName;
    }

    public int getGender() {
        return this.gender;
    }

    public int getUid() {
        return this.uid;
    }

    public String getDob() {
        return this.dob;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCreatedAt(){
        return this.created_at;
    }

    public String getUpdatedAt(){
        return this.updated_at;
    }
}