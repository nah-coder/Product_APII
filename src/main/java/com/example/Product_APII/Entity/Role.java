package com.example.Product_APII.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Size(max = 256)
    @Column(name = "role_name", length = 256)
    private String roleName;

    @Size(max = 256)
    @Column(name = "description", length = 256)
    private String description;


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
}