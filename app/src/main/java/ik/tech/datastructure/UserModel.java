package ik.tech.datastructure;

public class UserModel {
    private String username;
    private String email;
    private String password;

    public UserModel(){

    }
    public UserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
