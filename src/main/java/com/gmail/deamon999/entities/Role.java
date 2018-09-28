package com.gmail.deamon999.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CustomUser> getCustomUsers() {
        return customUsers;
    }

    public void setCustomUsers(List<CustomUser> customUsers) {
        this.customUsers = customUsers;
    }
}
