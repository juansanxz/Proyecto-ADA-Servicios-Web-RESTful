package edu.escuelaing.ieti.controller.user;

public class UserDto {
    String name;
    String lastName;
    String email;
    String password;

    public UserDto()
    {
    }


    public UserDto(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDto(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
