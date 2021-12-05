package ik.tech.datastructure;

public class UserModel {
    private String username;
    private String email;
    private String password;
    private String id;

    public UserModel(){

    }
    public UserModel(String username, String email, String password, String id) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
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
