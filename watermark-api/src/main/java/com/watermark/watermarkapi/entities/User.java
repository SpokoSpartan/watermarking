package com.watermark.watermarkapi.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    private String name;
    private String surname;
    private String password;
    private String email;
    @CreationTimestamp
    @Column(columnDefinition="TIMESTAMP DEFAULT NOW()")
    private Timestamp createdOn;

    protected User() {
    }

    public User(String name, String surname, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return String.format(
                "User[name='%s', surname='%s', password='%s', email='%s']", name, surname,
                password, email);
    }
}
