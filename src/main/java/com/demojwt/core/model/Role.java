package com.demojwt.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends PersistedObject {
    @Column(unique = true, length = 30)
    private String role;
    private String description;
}
