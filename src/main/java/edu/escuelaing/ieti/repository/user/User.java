package edu.escuelaing.ieti.repository.user;

import edu.escuelaing.ieti.controller.user.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.Serializable;
import java.util.*;

@Document(collection = "user_collection")
public class User{



    @Id
    private String id;
    private Date createdAt;
    private String name;
    private String lastName;

    @Indexed( unique = true )
    private String email;
    String passwordHash;

    List<RoleEnum> roles;


    public User() {
    }

    public User(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = new Date();
    }

    public User( UserDto userDto )
    {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
        createdAt = new Date();
        roles = new ArrayList<>( Collections.singleton( RoleEnum.USER ) );
        passwordHash = BCrypt.hashpw( userDto.getPassword(), BCrypt.gensalt() );
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public List<RoleEnum> getRoles()
    {
        return roles;
    }


    public void update( UserDto userDto )
    {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        if ( userDto.getPassword() != null )
        {
            this.passwordHash = BCrypt.hashpw( userDto.getPassword(), BCrypt.gensalt() );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(createdAt, user.createdAt) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, name, lastName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

