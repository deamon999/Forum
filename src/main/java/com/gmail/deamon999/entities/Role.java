package com.gmail.deamon999.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    private long id;
    private String role;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<CustomUser> customUsers = new ArrayList<CustomUser>();

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" + role + "}";
    }
}
