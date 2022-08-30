package com.example.Error_Notes.models;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
public class UserDto {
    private Long iduser;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String Adresse;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
