package com.example.UserService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public Role(int roleId) {
        this.roleId = roleId;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable = false)
    private String roleName;

}
