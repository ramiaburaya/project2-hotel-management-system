package com.example.hotelmangment.User.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
@RequiredArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(unique = true, nullable = false, name = "roleName")
    @NonNull
    private String name;

}