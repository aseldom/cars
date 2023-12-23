package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_user")
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "login")
    private String email;

    private String password;

    private String phone;

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + email + '\''
                + ", password='" + password + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}