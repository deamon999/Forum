package com.gmail.deamon999.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomUser {
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    private String email;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public CustomUser(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public CustomUser(String login, String password, Role role, String email, String phone) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    public CustomUser(String login, String password, String email, String phone) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
