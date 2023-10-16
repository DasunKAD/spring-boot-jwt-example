package com.demojwt.core.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_details")
public class Group extends PersistedObject {

    @Column(unique = true, length = 30)
    private String name;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "groups_role",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public Group(String name, String description, List<Role> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
