package com.example.hotelmangment.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "USERS")
@Schema(description = "User entity representing a system user")
public class UserInfo {

    @Schema(description = "Unique identifier of the user", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Schema(description = "Username of the user", required = true, example = "john_doe")
    @NonNull
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Schema(description = "Password of the user", required = true, example = "Password@123")
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Schema(description = "Role of the user", required = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    @NonNull
    private UserRole role;
}
