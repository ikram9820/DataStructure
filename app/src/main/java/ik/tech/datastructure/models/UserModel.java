package ik.tech.datastructure.models;

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String id;

    public UserModel(){

    }
    public UserModel(String name, String email, String password, String id) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public String getUsername() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
